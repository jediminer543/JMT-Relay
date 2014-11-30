var commander = require('commander');
var ws = require('ws');

//Used to validate ip's. Source: http://stackoverflow.com/questions/17987015/ip-address-validation-with-proper-dots-in-between (Thanks)
var ipPattern = /\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/;

//
commander.version('0.5.0-pre');
commander.option('-p, --port [port]', 'port to open on');
commander.option('-a, --address [address]', 'address to open on (IPv4)');

commander.parse(process.argv);

var ipaddress;
var port;

console.log(!pattern.test(commander.address));

if (commander.address == true || !ipPattern.test(commander.address) || commander.address == null) {
	ipaddress = process.env.OPENSHIFT_NODEJS_IP || process.env.IP ||  "127.0.0.1";
}
else {
	ipaddress = commander.address;
}

if (commander.port == true || commander.port >= 65535 || commander.port <= 0 || commander.port == null) {
	port = process.env.OPENSHIFT_NODEJS_PORT || process.env.PORT || 8000; 
}
else {
	port = commander.port;
}

//array variables
TYPE = 0;
SOURCE = 1;
TARGET = 2;
TARGET_TYPE = 3;
DATA = 4;
NAME = 5;
IPORT = 6;
EPORT = 7;

var accept = ["accept", "relay"];
var deny = ["deny", "relay"];

var hostCount = 0;
var clientCount = 0;

var hostnames = [];
var clientConnections = [null];
var serverConnections = [null];

var mode = "old";

function removeArray(array, item) {
	var index = array.indexOf(item);
	if (index > -1) {
	    array.splice(index, 1);
	}
}

var server = new WSserver({port : port, host:ipaddress});

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
		pMessage = JSON.parse(message);
		if (pMessage instanceof Array){
			if (pMessage[TYPE] == "host"){
				handleHost(pMessage, socket);
			}
			else if(pMessage[TYPE] == "client") {
				handleClient(pMessage, socket);
			}
			else if(pMessage[TYPE] == "watcher") {
				handleRequest(pMessage, socket);
			}
			else if(pMessage[TYPE] == "request") {
				handleRequest(pMessage, socket);
			}
			else if(pMessage[TYPE] == "data") {
				handleData(pMessage, socket);
			}
		}
		else {
			console.log("Invalid Message!!")
		}
	});
});
console.log("Server bound.");


function handleHost(pMessage, socket) {
	if (mode == "old") {
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
	else if (mode == "controller") {
		console.log("Denied New Host, Trying to using old system");
		deny[TARGET] = pMessage[NAME];
		socket.send(JSON.stringify(deny));
	}
	else if (mode == "backend") {
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
}

function handleClient(pMessage, socket) {
	if (mode == "old") {
		socket.isAccepted = true;
		console.log("New Client");
		clientConnections[clientConnections.length] = socket;
	}
	else if (mode == "controller") {
		console.log("Client attemptin access, killing.");
		socket.close();
	}		
	else if (mode == "backend") {
		//TODO
		socket.isAccepted = true;
		console.log("New Client");
		clientConnections[clientConnections.length] = socket;
	}
}

function handleWatcher(pMessage, socket) {
	if (mode == "old") {
		console.log("Unsupported connection");
		socket.close();
	}
	else if (mode == "controller") {
		//TODO
	}
	else if (mode == "backend") {
		console.log("Overwatch connection to backend!!");
		socket.close();
	}
}

function handleRequest(pMessage, socket) {
	if (mode == "old") {
		//TODO
	}
	else if (mode == "controller") {
		//TODO
	}		
	else if (mode == "backend") {
		//TODO
	}
}

function handleData(pMessage, socket) {
	if (mode == "old") {
		if (pMessage[TARGET_TYPE] == "host") {
			if (debug) { console.log("Data For Clients"); }
			for (var i = 1; i < serverConnections.length; i++) {
				try {
					serverConnections[i].send(message);
				}
				catch (ex) {}
			}
		}
		else if (pMessage[TARGET_TYPE] == "client") {
			if (debug) { console.log("Data For Clients"); }
			for (var i = 1; i < clientConnections.length; i++) {
				try {
					clientConnections[i].send(message);
				}
				catch (ex) {}
			}
		}
	}
	else if (mode == "controller") {
		console.log("Invalid Message!!, Data isnt for clients")
	}		
	else if (mode == "backend") {
		if (pMessage[TARGET_TYPE] == "host") {
			if (debug) { console.log("Data For Clients"); }
			for (var i = 1; i < serverConnections.length; i++) {
				try {
					serverConnections[i].send(message);
				}
				catch (ex) {}
			}
		}
		else if (pMessage[TARGET_TYPE] == "client") {
			if (debug) { console.log("Data For Clients"); }
			for (var i = 1; i < clientConnections.length; i++) {
				try {
					clientConnections[i].send(message);
				}
				catch (ex) {}
			}
		}
	}
}


