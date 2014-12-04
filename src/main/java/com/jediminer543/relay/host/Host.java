package com.jediminer543.relay.host;

import com.jediminer543.relay.remotes.Remote;

public class Host extends Thread {
	
	protected String url;
	protected String port;
	protected Remote remote;
	public String getUrl() {
		return url;
	}
	public String getPort() {
		return port;
	}
	public Remote getRemote() {
		return remote;
	}

	public Host() {
		super(new Runnable() {
			public void run() {
				//TODO
				
			}
		});
	}
}
