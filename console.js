/**
 * New node file
 */

var ws = require("ws");
var commander = require("commander");
commander
	.version("0.10.0")
	.option('-R, --relay <relay>', "Remote Address to connect to (in URI form)", "ws://127.0.0.1:8000")
	.option('-N, --target-name <name>', "Name to send/recieve to", "default")
	.option('-a, --local-address <address>', "Local Address to send/recieve from", "127.0.0.1")
	.option('-p, --local-port <port>', "Local port to send/recieve from", 8000)
	.option('-h, --host', "Switch to host mode", false)
	.option('-d, --debug', "Enable debug connections (NYI)", false)
	.parse(process.argv);

var remote = commander.relay;
var target = commander.targetName;
var port = commander.localPort;
var address = commander.localAddress;
var host = (commander.host === undefined) ? false : commander.host;
var debug = (commander.debug === undefined) ? false : commander.debug;

console.log("Remote: " + remote);
console.log("Port: " + port);
console.log("address: " + address);
console.log("host: " + host);
console.log("debug: " + debug);

var connectors = require('./connectors');
if (host) {
	connectors.createHost(target, port, address, remote);
} else {
	connectors.createClient(port, address, remote, target);
}
