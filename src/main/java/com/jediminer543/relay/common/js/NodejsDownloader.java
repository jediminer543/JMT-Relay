package com.jediminer543.relay.common.js;

import java.io.File;
import java.io.IOException;

import com.jediminer543.util.config.Config;

public class NodejsDownloader {

	
	
	
	
	
	
	public static class URLConfig {
		
		public static Config config;
		
		static {
			try {
				File cfgFile = new File("nodejsURLs.cfg");
				if (!cfgFile.exists()) {
					cfgFile.createNewFile();
					config = new Config(cfgFile);
					config.set("Win.32", "http://nodejs.org/dist/v0.10.32/node.exe");
					config.set("Win.64", "http://nodejs.org/dist/v0.10.32/x64/node.exe");
					config.set("Linux.32", "http://nodejs.org/dist/v0.10.32/node-v0.10.32-linux-x86.tar.gz");
					config.set("Linux.64", "http://nodejs.org/dist/v0.10.32/node-v0.10.32-linux-x64.tar.gz");
				}
				else {
					config = new Config(cfgFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public String getURL(String configNode) {
			return config.readString(configNode);
		}
	}
	
	public static enum OSType {
		Win32(OS.Windows, Arch.x86, "Win.32"),
		Win64(OS.Windows, Arch.x64, "Win.64");
		
		OS os;
		Arch arch;
		String configURLNode;
		
		public OS getOs() {
			return os;
		}

		public Arch getArch() {
			return arch;
		}

		public String getConfigURLNode() {
			return configURLNode;
		}
		
		OSType(OS os, Arch arch, String configURLNode) {
			this.os = os;
			this.arch = arch;
			this.configURLNode = configURLNode;
		}
		
		public static enum OS {
			Windows,
			Linux,
			Mac;
		}
		
		public static enum Arch{
			x86,
			x64;
		}
	}
}
