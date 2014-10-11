package com.jediminer543.relay.common.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextPane;
import com.jediminer543.relay.server.gui.Server;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Base {

	private JFrame frmJedi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Base window = new Base();
					window.frmJedi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Base() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJedi = new JFrame();
		frmJedi.setTitle("Jedirelay Launcher");
		frmJedi.setResizable(false);
		frmJedi.setBounds(100, 100, 305, 269);
		frmJedi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmJedi.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jedirelay");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Client");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmClient = new JMenuItem("Server");
		mnNewMenu.add(mntmClient);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JCheckBoxMenuItem chckbxmntmReconect = new JCheckBoxMenuItem("Auto-Reconect");
		chckbxmntmReconect.setSelected(true);
		mnOptions.add(chckbxmntmReconect);
		
		JMenuItem mntmDefaultServer = new JMenuItem("Default server...");
		mntmDefaultServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelayChanger.main(null);
			}
		});
		mnOptions.add(mntmDefaultServer);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 0, 0, 0, 22, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmJedi.getContentPane().setLayout(gridBagLayout);
		
		JButton btnLaunchClient = new JButton("Launch Client");
		btnLaunchClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnLaunchClient = new GridBagConstraints();
		gbc_btnLaunchClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLaunchClient.insets = new Insets(0, 0, 5, 5);
		gbc_btnLaunchClient.gridx = 1;
		gbc_btnLaunchClient.gridy = 1;
		frmJedi.getContentPane().add(btnLaunchClient, gbc_btnLaunchClient);
		
		JButton btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.main(null);
				frmJedi.dispose();
			}
		});
		GridBagConstraints gbc_btnLaunchServer = new GridBagConstraints();
		gbc_btnLaunchServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLaunchServer.insets = new Insets(0, 0, 5, 5);
		gbc_btnLaunchServer.gridx = 3;
		gbc_btnLaunchServer.gridy = 1;
		frmJedi.getContentPane().add(btnLaunchServer, gbc_btnLaunchServer);
		
		JTextPane txtpnJedirelaySystemWebsite = new JTextPane();
		txtpnJedirelaySystemWebsite.setText("Jedirelay System\r\nWebsite: ...\r\nInspired by ...\r\nSource: ...\r\nAuthor: Jediminer543");
		txtpnJedirelaySystemWebsite.setEditable(false);
		GridBagConstraints gbc_txtpnJedirelaySystemWebsite = new GridBagConstraints();
		gbc_txtpnJedirelaySystemWebsite.gridheight = 5;
		gbc_txtpnJedirelaySystemWebsite.gridwidth = 3;
		gbc_txtpnJedirelaySystemWebsite.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnJedirelaySystemWebsite.fill = GridBagConstraints.BOTH;
		gbc_txtpnJedirelaySystemWebsite.gridx = 1;
		gbc_txtpnJedirelaySystemWebsite.gridy = 2;
		frmJedi.getContentPane().add(txtpnJedirelaySystemWebsite, gbc_txtpnJedirelaySystemWebsite);
	}

}
