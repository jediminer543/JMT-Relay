//Required modules
var net = require('net');
var WebSocket = require('ws');

//Vars for connecting
var host = process.argv[2] || "ws://localhost";
var port = process.argv[3] || "8000";
var url = host + ":" + port;
var pword = process.argv[4] || "password";
var name = process.argv[5] || "host";
var iPort = parseInt(process.argv[6]) || 25565;
var ePort = parseInt(process.argv[7]) || 18888;

//array variables
TYPE = 0;
SOURCE = 1;
TARGET = 2;
TARGET_TYPE = 3;
DATA = 4;
NAME = 5;
IPORT = 6;
EPORT = 7;

var relay = new WebSocket(url);
var server = net.connect({ port: iPort, allowHalfOpen: true});
var lastPacket = null;

relay.on('open', function() {
	
	var host = [];
	host[TYPE] = "host";
	host[NAME] = name;
	host[IPORT] = iPort;
	host[EPORT] = ePort;
	relay.send(JSON.stringify(host));
});

relay.on('message', function(message) {
	//console.log(message);
	pMessage = JSON.parse(message);
	if (pMessage instanceof Array){
		if (pMessage[TYPE] == "data"){
			if (pMessage[SOURCE] == "client"){
				if (pMessage[TARGET] == name) {
					//console.log(new Buffer(pMessage[DATA]).constructor);
					try {
					server.write(new Buffer(pMessage[DATA], 'hex'));
					}
					catch (ex) {
					}
				}
			}
		}
		if (pMessage[TYPE] == "accept"){
			console.log("Connection Accepted!!")
		}
		if (pMessage[TYPE] == "deny"){
			relay.close();
			console.log("Name Invalid, Terminating!!")
		}
	}
	else {
		console.log("Invalid Message!!")
	}
});

server.on('close', function() {
	relay.close();
	console.log("Conection closed");

});

server.on('data', function(data) {
	
	//console.log(data.constructor);
	var arData = [];
	arData[TYPE] = "data";
	arData[SOURCE] = name;
	arData[TARGET] = "client";
	arData[DATA] = data.toString('hex');
	relay.send(JSON.stringify(arData));
});