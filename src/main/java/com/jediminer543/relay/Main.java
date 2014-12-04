package com.jediminer543.relay;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.jediminer543.relay.host.HostManager;
import com.jediminer543.relay.remotes.AddRemote;
import com.jediminer543.relay.remotes.ShowRemotes;

public class Main {
	
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
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
				HostManager.setVisibility(true);
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
	
}
