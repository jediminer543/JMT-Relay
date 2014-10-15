//server variables
var pword = "password";

/**
 * Web socket server
 */
var WSserver = require("ws").Server;

// Server variables
var ipaddress = process.env.OPENSHIFT_NODEJS_IP || process.env.IP ||  "127.0.0.1";
var port      = process.env.OPENSHIFT_NODEJS_PORT || process.env.PORT || 8000;

//array variables
TYPE = 0;
SOURCE = 1;
TARGET = 2;
TARGET_TYPE = 3;
DATA = 4;
NAME = 5;
IPORT = 6;
EPORT = 7;

//list of hosts
var hostnames = [];

var accept = ["accept", "relay"];
var deny = ["deny", "relay"]

var clientConnections = [null];
var serverConnections = [null];

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
	socket.hostname = null;
	
	socket.on('close', function() { 
		if (socket.isAccepted) {
		removeArray(hostnames, socket.hostname);
		removeArray(serverConnections, socket);
		removeArray(clientConnections, socket);
		console.log("Connector  Lost");
		console.log("Hostnames: " + JSON.stringify(hostnames));
		}
	});
	
	
	socket.on('message', function(message) {
		//console.log(message);
		pMessage = JSON.parse(message);
		if (pMessage instanceof Array){
			if (pMessage[TYPE] == "host"){
				console.log(pMessage[NAME]);
				console.log(hostnames.indexOf(pMessage[NAME]));
				if (hostnames.indexOf(pMessage[NAME]) === -1) {
					socket.isAccepted = true;
					console.log("Accepted New Host");
					socket.isHost = true;
					socket.hostname = pMessage[NAME];
					hostnames[hostnames.length] = pMessage[NAME];
					serverConnections[serverConnections.length] = socket;
					accept[TARGET] = pMessage[NAME];
					socket.send(JSON.stringify(accept));
				}
				else
				{
					console.log("Denied New Host");
					deny[TARGET] = pMessage[NAME];
					socket.send(JSON.stringify(deny));
				}
				
			}
			else if(pMessage[TYPE] == "client") {
				socket.isAccepted = true;
				console.log("New Client");
				clientConnections[clientConnections.length] = socket;
			}
			else if(pMessage[TYPE] == "data") {
				if (pMessage[SOURCE] == "client") {
					//TODO:Debug:console.log("Data For Servers");
				for (var i = 1; i < serverConnections.length; i++) {
					//console.log(i);
					try {
						serverConnections[i].send(message);
					}
					catch (ex) {}
				}
				}
				else {//if (pMessage[TARGET].indexOf("")) {
					//TODO:Debug:console.log("Data For Clients");
				for (var i = 1; i < clientConnections.length; i++) {
					console.log(i);
					try {
						clientConnections[i].send(message);
					}
					catch (ex) {}
				}
				}
			}
		}
		else {
			console.log("Invalid Message!!")
		}
	});
});
console.log("Server bound.");