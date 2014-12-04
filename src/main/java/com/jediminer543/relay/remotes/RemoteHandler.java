package com.jediminer543.relay.remotes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.jediminer543.util.config.Config;

public class RemoteHandler {
	static Config config;
	static boolean inited;
	public static String cfgFileName = "relays.cfg";
	public static File cfgFile;
	
	public static void init() {
		if (!inited) {
			try {
				cfgFile = new File(cfgFileName);
				if (!cfgFile.exists()) {
					cfgFile.createNewFile();
				}
				config = new Config(cfgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void checkInit() {
		if (!inited) {
			init();
		}
	}
	
	public static void addRemote(String name, String URL, String port) throws IOException {
		checkInit();
		config.set(name, true);
		config.set(name+".name", name);
		config.set(name+".url", URL);
		config.set(name+".port", port);
	}
	
	public static void addRemote(Remote remote) throws IOException {
		addRemote(remote.name, remote.url, remote.port);
	}
	
	public static void removeRemote(String name) throws IOException {
		checkInit();
		config.set(name, false);
	}
	
	public static ArrayList<Remote> getRemotes() {
		checkInit();
		ArrayList<Remote> remotes = new ArrayList<Remote>();
		ArrayList<String> keys = config.getKeys();
		for (String key:keys) {
			if (config.readBoolean(key)) {
				Remote remote = new Remote();
				remote.name = config.readString(key+".name");
				remote.url = config.readString(key+".url");
				remote.port = config.readString(key+".port");
				remotes.add(remote);
			}
		}
		return remotes;
	}
}
