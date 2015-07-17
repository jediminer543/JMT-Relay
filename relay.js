/**
 * Relay.js
 * 
 * @author Jediminer543
 */



/**
 * Web socket server
 */
var WSserver = require("ws").Server;

// Server variables
var ipaddress = process.env.OPENSHIFT_NODEJS_IP || process.env.IP ||  "127.0.0.1";
var port      = process.env.OPENSHIFT_NODEJS_PORT || process.env.PORT || 8000;

//array variables
var DataIDs = {};
DataIDs.TYPE = 0;
DataIDs.SOURCE = 1;
DataIDs.TARGET = 2;
DataIDs.DATA = 3;

//list of hosts
var hostnames = [];
var connections = [];

var accept = ["accept", "relay"];
var deny = ["deny", "relay"];

var server = new WSserver({port : port, host:ipaddress});

function removeArray(array, item) {
	var index = array.indexOf(item);
	if (index > -1) {
	    array.splice(index, 1);
	}
}

/**
 * Informs the server what to do when a connection is recieved.
 */
server.on('connection', function(socket){
	
	socket.isAccepted = false;
	socket.isHost = false;
	socket.id = null;
	
	socket.on('close', function() { 
		if (socket.isAccepted) {
		removeArray(hostnames, socket.id);
		removeArray(connections, socket);
		console.log("Connector  Lost");
		console.log("Hostnames: " + JSON.stringify(hostnames));
		}
	});
	
	
	socket.on('message', function(message) {
		var pMessage = JSON.parse(message);
		console.log(pMessage);
		if (pMessage instanceof Array){
			if (pMessage[DataIDs.TARGET] === "relay") {
				if (pMessage[DataIDs.TYPE] === "host") {

					console.log(pMessage[DataIDs.DATA][1]);
					console.log(hostnames.indexOf(pMessage[DataIDs.DATA][1]));
					if (hostnames.indexOf(pMessage[DataIDs.DATA][1]) === -1) {

						socket.isAccepted = true;
						console.log("Accepted New Host");
						socket.isHost = true;
						socket.id = pMessage[DataIDs.DATA][1];
						hostnames[hostnames.length] = pMessage[DataIDs.DATA][1];
						connections[connections.length] = socket;
						accept[DataIDs.TARGET] = pMessage[DataIDs.DATA][1];
						socket.send(JSON.stringify(accept));
					}
					else {

						console.log("Denied New Host");
						deny[DataIDs.TARGET] = pMessage[DataIDs.DATA][1];
						socket.send(JSON.stringify(deny));
					}

				}
				else if(pMessage[DataIDs.TYPE] === "client") {

					socket.isAccepted = true;
					console.log("New Client");
					socket.id = pMessage[DataIDs.SOURCE];
					connections[connections.length] = socket;
				}
			} else {
				for (var i = 0; i < connections.length; i++) {
					if (connections[i].id === pMessage[DataIDs.TARGET]) {
						try {
							connections[i].send(message);
						} catch (ex) {}
						break;
					} else if (connections[i].id === pMessage[DataIDs.TARGET].substr(0,36)) {
						try {
							connections[i].send(message);
						} catch (ex) {}
						break;
					}
				}
			}
		}
		else {
			console.log("Invalid Message!!");
		}
	});
});
console.log("Server bound.");