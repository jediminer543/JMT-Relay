package com.jediminer543.relay.server.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.GridBagLayout;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Server {

	private JFrame frame;
	private JTextField txtPassword;
	private JTextField txtName;
	private JTextField txtLPort;
	private JTextField txtEPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
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
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 251);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jedirelay");
		menuBar.add(mnNewMenu);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblRelayPassword = new JLabel("Relay Password");
		GridBagConstraints gbc_lblRelayPassword = new GridBagConstraints();
		gbc_lblRelayPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelayPassword.anchor = GridBagConstraints.EAST;
		gbc_lblRelayPassword.gridx = 1;
		gbc_lblRelayPassword.gridy = 1;
		frame.getContentPane().add(lblRelayPassword, gbc_lblRelayPassword);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 2;
		gbc_txtPassword.gridy = 1;
		frame.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 2;
		frame.getContentPane().add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 2;
		frame.getContentPane().add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblLocalPort = new JLabel("Local Port");
		GridBagConstraints gbc_lblLocalPort = new GridBagConstraints();
		gbc_lblLocalPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalPort.anchor = GridBagConstraints.EAST;
		gbc_lblLocalPort.gridx = 1;
		gbc_lblLocalPort.gridy = 3;
		frame.getContentPane().add(lblLocalPort, gbc_lblLocalPort);
		
		txtLPort = new JTextField();
		GridBagConstraints gbc_txtLPort = new GridBagConstraints();
		gbc_txtLPort.insets = new Insets(0, 0, 5, 0);
		gbc_txtLPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLPort.gridx = 2;
		gbc_txtLPort.gridy = 3;
		frame.getContentPane().add(txtLPort, gbc_txtLPort);
		txtLPort.setColumns(10);
		
		JLabel lblExternalPort = new JLabel("External Port");
		GridBagConstraints gbc_lblExternalPort = new GridBagConstraints();
		gbc_lblExternalPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblExternalPort.anchor = GridBagConstraints.EAST;
		gbc_lblExternalPort.gridx = 1;
		gbc_lblExternalPort.gridy = 4;
		frame.getContentPane().add(lblExternalPort, gbc_lblExternalPort);
		
		txtEPort = new JTextField();
		GridBagConstraints gbc_txtEPort = new GridBagConstraints();
		gbc_txtEPort.insets = new Insets(0, 0, 5, 0);
		gbc_txtEPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEPort.gridx = 2;
		gbc_txtEPort.gridy = 4;
		frame.getContentPane().add(txtEPort, gbc_txtEPort);
		txtEPort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.gridwidth = 2;
		gbc_btnConnect.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnect.gridx = 1;
		gbc_btnConnect.gridy = 6;
		frame.getContentPane().add(btnConnect, gbc_btnConnect);
	}

}
