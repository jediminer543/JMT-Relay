/**
 * Connectors.js
 * 
 * This creates either host or client components of the websocket relay
 * system. All functions return an object for tracking purposes.
 * 
 * @author Jediminer543
 * 
 * @since 1.0.0
 */

// Load Libraries
var net = require('net');
var ws = require('ws');
var uuid = require('node-uuid');

//NYI:var hosts = [];

// Defines data positions
var DataIDs = {};
DataIDs.TYPE = 0;
DataIDs.SOURCE = 1;
DataIDs.TARGET = 2;
DataIDs.DATA = 3;

function removeArray(array, item) {
	var index = array.indexOf(item);
	if (index > -1) {
	    array.splice(index, 1);
	}
}

/**
 * 
 * 
 * 
 * @param name Name of process
 * @param localPort
 * @param localAddress
 * @param remoteURI
 * @returns Running host
 */
function createHost (name, localPort, localAddress, remoteURI) {
	var host = {};
	
	//Creation Data Vars
	//Unique host ID
	host.id = uuid.v4();
	// Name Of Host
	host.name = name;
	// Location Of Host's target
	host.localPort = localPort;
	host.localAddress = localAddress;
	
	// Connections
	host.relay = new ws(remoteURI);
	host.servers = {};
	
		//net.connect({ host: localAddress, port: localPort, allowHalfOpen: true});

	host.relay.on('open', function() {
		
		var packet = [];
		packet[DataIDs.TYPE] = "host";
		packet[DataIDs.SOURCE] = host.id;
		// Data Format: host localPort (for Clients to Mirror), Host Name (for Searching), and UDP activation of Host ()
		packet[DataIDs.DATA] = [localPort, name, false];
		packet[DataIDs.TARGET] = "relay";
		host.relay.send(JSON.stringify(packet));
	});

	host.relay.on('message', function(message) {
		var pMessage = JSON.parse(message);
		if (pMessage instanceof Array){
			if (pMessage[DataIDs.TYPE] === "new") {
				console.log("Instantating New Connection");
				host.servers[pMessage[DataIDs.SOURCE]] = net.connect({ host: localAddress, port: localPort, allowHalfOpen: true});
				host.servers[pMessage[DataIDs.SOURCE]].on('close', function() {
					console.log("Connection closed");
				});

				host.servers[pMessage[DataIDs.SOURCE]].on('data', function(data) {
					var arData = [];
					arData[DataIDs.TYPE] = "data";
					arData[DataIDs.SOURCE] = name;
					arData[DataIDs.TARGET] = pMessage[DataIDs.SOURCE];
					arData[DataIDs.DATA] = data.toString('hex');
					console.log(DataIDs.SOURCE + ":" + "Sending: " + JSON.stringify(arData[DataIDs.DATA]));
					host.relay.send(JSON.stringify(arData));
				});
				
				host.servers[pMessage[DataIDs.SOURCE]].on('error', function (exc) {
				    console.log("Handling Error: " + exc.name + ". (ECONNRESET is normal)");
				});
			}
			if (pMessage[DataIDs.TYPE] === "end") {
				host.servers[pMessage[DataIDs.SOURCE]].destroy();
				delete host.servers[pMessage[DataIDs.SOURCE]];
			}
			if (pMessage[DataIDs.TYPE] === "data") {
				console.log(DataIDs.SOURCE + ":" + "Recieving: " + JSON.stringify(pMessage[DataIDs.DATA]));
				if (pMessage[DataIDs.TARGET] === name) {
					try {
						host.servers[pMessage[DataIDs.SOURCE]].write(new Buffer(pMessage[DataIDs.DATA], 'hex'));
					}
					catch (ex) {
						console.log(ex.message);
					}
				}
			}
			if (pMessage[DataIDs.TYPE] === "accept") {
				console.log("Connection Accepted!!");
			}
			if (pMessage[DataIDs.TYPE] === "deny") {
				host.relay.close();
				console.log("Name Invalid, Terminating!!");
			}
		}
		else {
			console.log("Invalid Message!!");
		}
	});
	
	host.relay.on('close', function (code, message) {
		console.log("Server closed: " + message + "(" + code + ")");
	});
	
	return host;
}



function createClient(localPort, localAddress, remoteURI, targetName) {
	var client = {};
	
	// Client local server
	client.relay = new ws(remoteURI);
	client.localConnections = [];
	
	//Midrun Data
	client.id = uuid.v1();
	client.iid = 0;
	
	client.targetName = targetName;
	client.localPort = localPort;
	client.localAddress = localAddress;
	
	client.relay.on('open', function() {
		var packet = [];
		packet[DataIDs.TYPE] = "client";
		packet[DataIDs.SOURCE] = client.id;
		packet[DataIDs.TARGET] = "relay";
		packet[DataIDs.DATA] = targetName;
		client.relay.send(JSON.stringify(packet));
	});
	
	client.relay.on('message', function(message) {
		console.log("Recieving: " + message);
		var pMessage = JSON.parse(message);
		if (pMessage instanceof Array){
			if (pMessage[DataIDs.TYPE] === "data"){
				if (pMessage[DataIDs.SOURCE] === targetName) {
					if (pMessage[DataIDs.TARGET].substr(0,36) === client.id) {
						for (var i = 0; i < client.localConnections.length; i++) {
							console.log(pMessage[DataIDs.TARGET] +":"+ client.localConnections[i].id);
							if (pMessage[DataIDs.TARGET] === client.localConnections[i].id) {
								try {
									console.log("Writing: " + pMessage[DataIDs.DATA]);
									client.localConnections[i].write(new Buffer(pMessage[DataIDs.DATA], 'hex'));
								} catch (ex) {}
							}
						}
					}
				}
			}
		}
		else {
			console.log("Invalid Message!!");
		}
	});
	
	//Local Data Transiever
	client.server = net.createServer({allowHalfOpen: false},function(socket) {
		
		client.localConnections.push(socket);
		
		client.iid++;
		socket.id =  client.id + "-" + client.iid;
		
		
		//Initialisation phase
		var packet = [];
		packet[DataIDs.TYPE] = "new";
		packet[DataIDs.SOURCE] = socket.id;
		packet[DataIDs.TARGET] = targetName;
		packet[DataIDs.DATA] = targetName;
		client.relay.send(JSON.stringify(packet));
		
		socket.on('data', function(data)
		{
			//console.log(data.toString('hex'));
			var arData = [];
			arData[DataIDs.TYPE] = "data";
			arData[DataIDs.SOURCE] = socket.id;
			arData[DataIDs.TARGET] = targetName;
			arData[DataIDs.DATA] = data.toString('hex');
			console.log(socket.id + ":" + "Sending: " + JSON.stringify(arData));
			client.relay.send(JSON.stringify(arData));
		});
		
		socket.on('error', function (exc) {
		    console.log("Handling Error: " + exc.name + ". (ECONNRESET is normal)");
		});
		
		socket.on('close', function () {
			removeArray(client.localConnections, socket);
			var packet = [];
			packet[DataIDs.TYPE] = "end";
			packet[DataIDs.SOURCE] = socket.id;
			packet[DataIDs.TARGET] = targetName;
			packet[DataIDs.DATA] = targetName;
			client.relay.send(JSON.stringify(packet));
		});
		
		
	});

	client.server.listen(localPort, localAddress);
	
	return client;
}

module.exports = {
		createHost: createHost,
		createClient: createClient
};