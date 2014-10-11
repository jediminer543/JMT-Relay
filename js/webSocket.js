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
DATA = 3;
NAME = 4;
IPORT = 5;
EPORT = 6;

//list of hosts
//TODO:store hosts:var hostnames = [];

var connections = [null];

var server = new WSserver({port : port, host:ipaddress});


/**
 * Informs the server what to do when a connection is recieved.
 */
server.on('connection', function(socket){
	
	socket.on('message', function(message) {
		//console.log(message);
		pMessage = JSON.parse(message);
		if (pMessage instanceof Array){
			if (pMessage[TYPE] == "host"){
				console.log("New Host");
				connections[connections.length] = socket;
			}
			else if(pMessage[TYPE] == "client") {
				console.log("New Client");
				connections[connections.length] = socket;
			}
			else if(pMessage[TYPE] == "data") {
				console.log(connections.length);
				for (var i = 1; i < connections.length; i++) {
					console.log(i);
					try {
					connections[i].send(message);
					}
					catch (ex) {}
				}
			}
		}
		else {
			console.log("Invalid Message!!")
		}
	});
});
console.log("Server bound.");