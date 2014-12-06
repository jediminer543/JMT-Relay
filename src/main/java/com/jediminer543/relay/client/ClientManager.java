package com.jediminer543.relay.client;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.jediminer543.relay.remotes.AddRemote;
import com.jediminer543.relay.remotes.ShowRemotes;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Canvas;
import java.awt.Insets;

public class ClientManager {
	
	//static ArrayList<Host> hosts = new ArrayList<Host>();
	
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnRelay = new JMenu("Relay");
		menuBar.add(mnRelay);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnRelay.add(mntmExit);
		
		JMenu mnRemotes = new JMenu("Remotes");
		menuBar.add(mnRemotes);
		
		JMenuItem mntmAdd = new JMenuItem("Add...");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRemote addRemote = new AddRemote();
				addRemote.setLocation(frame.getLocation().x + frame.getWidth(), frame.getLocation().y);
				addRemote.setVisible(true);
			}
		});
		mnRemotes.add(mntmAdd);
		
		JMenuItem mntmShow = new JMenuItem("Show...");
		mntmShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowRemotes showRemotes = new ShowRemotes();
				showRemotes.setLocation(frame.getLocation().x, frame.getLocation().y + frame.getHeight());
				showRemotes.setVisible(true);
			}
		});
		mnRemotes.add(mntmShow);
		
		JMenu mnHost = new JMenu("Host");
		menuBar.add(mnHost);
		
		JMenu mnPrefabs = new JMenu("Prefabs");
		mnHost.add(mnPrefabs);
		
		JMenuItem mntmFavorites = new JMenuItem("Favorites");
		mnHost.add(mntmFavorites);
		
		JMenuItem mntmNew = new JMenuItem("New...");
		mnHost.add(mntmNew);
		
		JMenuItem mntmManage = new JMenuItem("Manage...");
		mntmManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManager.setVisibility(true);
			}
		});
		mnHost.add(mntmManage);
		
		JMenu mnClient = new JMenu("Client");
		menuBar.add(mnClient);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmWiki = new JMenuItem("Wiki");
		mnHelp.add(mntmWiki);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
	
//	/**
//	 * 
//	 * 
//	 * @param host Host to add to storage 
//	 */
//	public static void addHost(Host host) {
//		checkInit();
//		hosts.add(host);
//	}
	
}
