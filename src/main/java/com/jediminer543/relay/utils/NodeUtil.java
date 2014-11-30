package com.jediminer543.relay.utils;

import java.io.IOException;

public class NodeUtil {
	
	public static void getCheckNode() {
		try {
			Process p1 = Runtime.getRuntime().exec("node");
			p1.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static enum OSDownloads {
		win32("http://nodejs.org/dist/v0.10.33/node.exe", "Windows", false),
		linux("http://nodejs.org/dist/v0.10.33/node-v0.10.33-linux-x86.tar.gz", "Linux", true),
		macosx("http://nodejs.org/dist/v0.10.33/node-v0.10.33-darwin-x86.tar.gz", "Mac", true);
		
		public String loc;
		public String os;
		public boolean compressed;
		
		OSDownloads(String loc, String os, boolean compressed) {
			this.loc = loc;
			this.os = os;
			this.compressed = compressed;
		}
		
	}
}
