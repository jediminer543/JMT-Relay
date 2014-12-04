package com.jediminer543.relay.remotes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowRemotes extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8123028181842958859L;
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Create the dialog.
	 */
	public ShowRemotes() {
		setResizable(false);
		setBounds(100, 100, 420, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{253, 0, 126, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRemotes = new JLabel("Remotes");
			GridBagConstraints gbc_lblRemotes = new GridBagConstraints();
			gbc_lblRemotes.gridwidth = 3;
			gbc_lblRemotes.insets = new Insets(0, 0, 5, 0);
			gbc_lblRemotes.gridx = 0;
			gbc_lblRemotes.gridy = 0;
			contentPanel.add(lblRemotes, gbc_lblRemotes);
		}
		{
			final ArrayList<String> remotes = new ArrayList<String>();
			for (Remote r:RemoteHandler.getRemotes()) {
				remotes.add(r.name + " -- " + r.url + ":" + r.port);
			}
			JList<String> list = new JList<String>();
			list.setModel(new AbstractListModel<String>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -7643863334393856960L;
				public int getSize() {
					return remotes.size();
				}
				public String getElementAt(int index) {
					return remotes.get(index);
				}
			});
			GridBagConstraints gbc_list = new GridBagConstraints();
			gbc_list.gridwidth = 3;
			gbc_list.fill = GridBagConstraints.BOTH;
			gbc_list.gridx = 0;
			gbc_list.gridy = 1;
			contentPanel.add(list, gbc_list);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
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
