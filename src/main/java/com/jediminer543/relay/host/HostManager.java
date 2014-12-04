package com.jediminer543.relay.host;

import java.util.ArrayList;

import javax.swing.JFrame;

public class HostManager {
	
	static ArrayList<Host> hosts = new ArrayList<Host>();
	
	private static JFrame frame;
	
	private static boolean initialised = false;
	
	private static void checkInit() {
		if (!initialised) {
			initialised = true;
			initialize();
		}
	}
	
	public static void setVisibility(boolean visibility) {
		checkInit();
		frame.setVisible(visibility);
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * 
	 * 
	 * @param host Host to add to storage 
	 */
	public static void addHost(Host host) {
		checkInit();
		hosts.add(host);
	}
	
}
