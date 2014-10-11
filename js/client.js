var net = require('net');
var WebSocket = require('ws');

var host = process.argv[2] || "ws://localhost";
var port = process.argv[3] || "8000";
var url = host + ":" + port;
var target = process.argv[4] || "host";
var ePort = parseInt(process.argv[7]) || 18888;

//array variables
TYPE = 0;
SOURCE = 1;
TARGET = 2;
DATA = 3;
NAME = 4;
IPORT = 5;
EPORT = 6;
var WSrelay = new WebSocket(url);
WSrelay.on('open', function() {
	
	var host = [];
	host[TYPE] = "client";
	host[TARGET] = target;
	host[EPORT] = ePort;
	WSrelay.send(JSON.stringify(host));
});
var server = net.createServer(function(socket) {
	
	//var open = false;
	var exData = [];
	
	socket.on('data', function(data)
	{
		//console.log(data.constructor);
		var arData = [];
		arData[TYPE] = "data";
		arData[SOURCE] = "client";
		arData[TARGET] = target;
		arData[DATA] = data;
		//console.log(arData.constructor);
		WSrelay.send(JSON.stringify(arData));
	});
	
	WSrelay.on('message', function(message) {
		//console.log(message);
		pMessage = JSON.parse(message);
		if (pMessage instanceof Array){
			if (pMessage[TYPE] == "data"){
				if (pMessage[SOURCE] == target){
					if (pMessage[TARGET] == target) {
						try {
						socket.write(new Buffer(pMessage[DATA]));
						}
						catch (ex) {
						server.listen(ePort);
						}
					}
				}
			}
		}
		else {
			console.log("Invalid Message!!")
		}
	});
	
	
});

server.listen(ePort);