package com.jediminer543.relay.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnector {

	Socket local = null;
	
	public ServerConnector(String host, int port) throws UnknownHostException, IOException
	{
		local = new Socket(host, port);
	}
	
	public void stuff()
	{
		//local.
	}
}
