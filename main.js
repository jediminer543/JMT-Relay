/**
 * GUI controler for electron
 */
var app = require('app');
var BrowserWindow = require('browser-window');
var ipc = require('ipc');

var mainWindow = null;
var licenceWindow = null;
var optionsWindow = null;
var subWindows = [];

var opening = false;

function openPage (file) {
	mainWindow.loadUrl('file://' + __dirname + '/' + file);
}

ipc.on("openMainPage", function(event, arg) {
	event.sender.send("return", "loading");
	openPage(arg);
});

ipc.on("requestMainClose", function(event, arg) {
	event.sender.send("return", "closing");
	mainWindow.close();
	mainWindow = null;
});

ipc.on("requestLicence", function(event, arg) {
	event.sender.send("return", "licenceing");
	if (!licenceWindow.isVisible()) { licenceWindow.show(); } else { licenceWindow.hide(); }
});

app.on('window-all-closed', function() {
		if (process.platform !== 'darwin') {
			app.quit();
		}
});

app.on('ready', function() {
	mainWindow = new BrowserWindow({width: 800, height: 600, frame: false, resizable:false});
	
	openPage("index.html");
	
	mainWindow.on('closed', function() {
		console.log("closed");
	    mainWindow = null;
	});
	
	licenceWindow = new BrowserWindow({width: 640, height: 480, frame: false, resizable:false, show:false});
	licenceWindow.loadUrl('file://' + __dirname + '/' + "licence.html");
	
	optionsWindow = new BrowserWindow({width: 800, height: 700, frame: false, resizable:false, show:false});
});