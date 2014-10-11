package com.jediminer543.relay.common.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RelayChanger extends JDialog {

	/**
	 * Because java?
	 */
	private static final long serialVersionUID = -4194805818771334748L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtRelayPort;
	private JTextField txtRelayAddress;
	private static RelayChanger dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new RelayChanger();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RelayChanger() {
		setBounds(100, 100, 450, 140);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {75, 175, 56, 75};
		gbl_contentPanel.rowHeights = new int[]{30, 20, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWebsocketRelayUrl = new JLabel("Websocket Relay URL:");
			GridBagConstraints gbc_lblWebsocketRelayUrl = new GridBagConstraints();
			gbc_lblWebsocketRelayUrl.gridwidth = 4;
			gbc_lblWebsocketRelayUrl.insets = new Insets(0, 0, 5, 0);
			gbc_lblWebsocketRelayUrl.gridx = 0;
			gbc_lblWebsocketRelayUrl.gridy = 0;
			contentPanel.add(lblWebsocketRelayUrl, gbc_lblWebsocketRelayUrl);
		}
		{
			JLabel lblAdress = new JLabel("Address:");
			GridBagConstraints gbc_lblAdress = new GridBagConstraints();
			gbc_lblAdress.anchor = GridBagConstraints.EAST;
			gbc_lblAdress.insets = new Insets(0, 0, 0, 5);
			gbc_lblAdress.gridx = 0;
			gbc_lblAdress.gridy = 1;
			contentPanel.add(lblAdress, gbc_lblAdress);
		}
		{
			txtRelayAddress = new JTextField();
			txtRelayAddress.setText("relay-jediminer543.rhcloud.com");
			GridBagConstraints gbc_txtRelayjediminerrhcloudcom = new GridBagConstraints();
			gbc_txtRelayjediminerrhcloudcom.insets = new Insets(0, 0, 0, 5);
			gbc_txtRelayjediminerrhcloudcom.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRelayjediminerrhcloudcom.gridx = 1;
			gbc_txtRelayjediminerrhcloudcom.gridy = 1;
			contentPanel.add(txtRelayAddress, gbc_txtRelayjediminerrhcloudcom);
			txtRelayAddress.setColumns(10);
		}
		{
			JLabel lblPort = new JLabel("Port:");
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.anchor = GridBagConstraints.EAST;
			gbc_lblPort.insets = new Insets(0, 0, 0, 5);
			gbc_lblPort.gridx = 2;
			gbc_lblPort.gridy = 1;
			contentPanel.add(lblPort, gbc_lblPort);
		}
		{
			txtRelayPort = new JTextField();
			txtRelayPort.setText("8000");
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 3;
			gbc_textField.gridy = 1;
			contentPanel.add(txtRelayPort, gbc_textField);
			txtRelayPort.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(txtRelayPort.getText());
						System.out.println(txtRelayAddress.getText());
						dispose();
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
