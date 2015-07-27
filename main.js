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
	console.log("Loading: " + 'file://' + __dirname + '/' + file);
	mainWindow.loadUrl('file://' + __dirname + '/' + file);
	console.log("Load Complete");
	//mainWindow.show();
}

ipc.on("openPage", function(event, arg) {
	openPage(arg);
});

//app.on('window-all-closed', function() {
//		if (process.platform !== 'darwin') {
//			app.quit();
//		}
//});

app.on('ready', function() {
	mainWindow = new BrowserWindow({width: 800, height: 600, frame: true, resizable:false});
	
	openPage("index.html");
	
	//mainWindow.on('closed', function() {
	    //mainWindow = null;
	//});
});