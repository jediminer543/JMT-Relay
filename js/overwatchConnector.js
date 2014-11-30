//Node addon modules
var commander = require('commander');
var ws = require('ws');

commander.version('0.5.0-pre');
commander.option('-p, --port [port]', 'port to connect on');
commander.option('-a, --address [address]', 'address to v on (IPv4)');
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