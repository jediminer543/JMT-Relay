//Node addon modules
var commander = require('commander');
var ws = require('ws');

//Used to validate ip's. Source: http://stackoverflow.com/questions/17987015/ip-address-validation-with-proper-dots-in-between (Thanks)
var ipPattern = /(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)/;

commander.version('0.5.0-pre');
commander.option('-p, --port [port]', 'port to connect on');
commander.option('-a, --address [address]', 'address to connect on (IPv4)');
commander.option('--force-port [port]', 'forces port to connect on');
commander.option('--force-address [address]', 'address to connect on (IPv4)');
commander.option('--getHosts', 'returns all hosts on websocket');
commander.option('--getNewHosts [initHost]', 'returns info of new host based off initial');
commander.option('--getBestFree', 'returns best backend\'s url');
commander.option('--getHostCount', 'returns count of all hosts (Debug)');
commander.option('--getClientCount', 'returns count of all clients (Debug)');
commander.option('--getTotalCount', 'returns count of all connections (Debug)');
commander.parse(process.argv);

var ipaddress;
var port;

if ((commander.address == true || !commander.address.indexOf("ws://") == 0 || commander.address == null)) {
	ipaddress = "ws://127.0.0.1";
}
else {
	if (!commander.address.indexOf("/", commander.address.length - "/".length) !== -1) {
		commander.address = commander.address.substring(0, commander.address.length - 1)
	}
	ipaddress = commander.address;
}

if (commander.port == true || commander.port >= 65535 || commander.port <= 0 || commander.port == null) {
	port = 8000; 
}
else {
	port = commander.port;
}

var url = ipaddress + ":" + port;
console.log(url);

TYPE = 0;
SOURCE = 1;
TARGET = 2;
TARGET_TYPE = 3;
DATA = 4;
NAME = 5;
IPORT = 6;
EPORT = 7;

//var relay = new ws(url);
//relay.on('open', function() {
//	var request = ["request"];
//	if (commander.getHosts) {
//		request[];
//		relay.send(JSON.stringify());
//	}
//});