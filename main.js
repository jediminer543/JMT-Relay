/**
 * GUI controler for electron
 */
var app = require('app');
var BrowserWindow = require('browser-window');
var ipc = require('ipc');

var mainWindow = null;
var subWindows = [];

var opening = false;

function openPage (file) {
	mainWindow.loadUrl('file://' + __dirname + '/' + file);
}

ipc.on("openPage", function(event, arg) {
	event.sender.send("return", "loading");
	openPage(arg);
});

app.on('window-all-closed', function() {
		if (process.platform !== 'darwin') {
			app.quit();
		}
});

app.on('ready', function() {
	mainWindow = new BrowserWindow({width: 800, height: 600, frame: true, resizable:false});
	
	openPage("index.html");
	
	mainWindow.on('closed', function() {
		console.log("closed");
	    mainWindow = null;
	});
	
	mainWindow.onbeforeunload = function (e) {
		
		return false;
	};
});