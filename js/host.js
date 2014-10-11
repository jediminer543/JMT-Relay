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
DATA = 3;
NAME = 4;
IPORT = 5;
EPORT = 6;

var relay = new WebSocket(url);
var server = net.connect(iPort);

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
					
					try {
					server.write(new Buffer(pMessage[DATA]));
					}
					catch (ex) {
					}
				}
			}
		}
	}
	else {
		console.log("Invalid Message!!")
	}
});

server.on('close', function() {
	console.log("Conection closed")

});

server.on('data', function(data) {
	
	var arData = [];
	arData[TYPE] = "data";
	arData[SOURCE] = name;
	arData[TARGET] = name;
	arData[DATA] = data;
	relay.send(JSON.stringify(arData));
});