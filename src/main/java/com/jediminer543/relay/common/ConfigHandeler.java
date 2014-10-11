package com.jediminer543.relay.common;

import java.io.File;
import java.io.IOException;

import com.jediminer543.util.config.Config;

public class ConfigHandeler {
	
	static String hostAddress = "relay-jediminer543.rhcloud.com";
	static int hostPort = 8000;
	
	public static String getHostAddress() {
		return hostAddress;
	}
	public static int getHostPort() {
		return hostPort;
	}
	
	static Config config = null;
	
	static
	{
		try {
			config = new Config(new File("config.cfg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
