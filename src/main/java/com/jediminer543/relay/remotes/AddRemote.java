package com.jediminer543.relay.remotes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AddRemote extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5597746059196562046L;
	private final JPanel contentPanel = new JPanel();
	private JSpinner textPort;
	private JTextField textURL;
	private JTextField textName;
	
	/**
	 * Create the dialog.
	 */
	public AddRemote() {
		setTitle("Add Remote");
		setBounds(100, 100, 409, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblAddRemote = new JLabel("Add Remote");
			GridBagConstraints gbc_lblAddRemote = new GridBagConstraints();
			gbc_lblAddRemote.gridwidth = 2;
			gbc_lblAddRemote.insets = new Insets(0, 0, 5, 0);
			gbc_lblAddRemote.gridx = 0;
			gbc_lblAddRemote.gridy = 1;
			contentPanel.add(lblAddRemote, gbc_lblAddRemote);
		}
		{
			JLabel lblName = new JLabel("Name");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 2;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			textName = new JTextField();
			textName.setText("Remote Name");
			textName.setColumns(10);
			GridBagConstraints gbc_textName = new GridBagConstraints();
			gbc_textName.insets = new Insets(0, 0, 5, 0);
			gbc_textName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textName.gridx = 1;
			gbc_textName.gridy = 2;
			contentPanel.add(textName, gbc_textName);
		}
		{
			JLabel lblUrl = new JLabel("URL");
			GridBagConstraints gbc_lblUrl = new GridBagConstraints();
			gbc_lblUrl.insets = new Insets(0, 0, 5, 5);
			gbc_lblUrl.gridx = 0;
			gbc_lblUrl.gridy = 3;
			contentPanel.add(lblUrl, gbc_lblUrl);
		}
		{
			textURL = new JTextField();
			textURL.setText("URL (No end slash)");
			GridBagConstraints gbc_textURL = new GridBagConstraints();
			gbc_textURL.insets = new Insets(0, 0, 5, 0);
			gbc_textURL.fill = GridBagConstraints.HORIZONTAL;
			gbc_textURL.gridx = 1;
			gbc_textURL.gridy = 3;
			contentPanel.add(textURL, gbc_textURL);
			textURL.setColumns(10);
		}
		{
			JLabel lblPort = new JLabel("Port");
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.insets = new Insets(0, 0, 0, 5);
			gbc_lblPort.gridx = 0;
			gbc_lblPort.gridy = 4;
			contentPanel.add(lblPort, gbc_lblPort);
		}
		{
			textPort = new JSpinner();
			textPort.setModel(new SpinnerNumberModel(8000, 1, 65535, 1));
			GridBagConstraints gbc_textPort = new GridBagConstraints();
			gbc_textPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_textPort.gridx = 1;
			gbc_textPort.gridy = 4;
			contentPanel.add(textPort, gbc_textPort);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							RemoteHandler.addRemote(textName.getText(), textURL.getText(), textPort.getModel().getValue().toString());
							setVisible(false);
							dispose();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
