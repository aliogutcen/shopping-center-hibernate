 package com.ogutcenali.controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setForeground(new Color(46, 46, 46));
		setTitle("Selection Screen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 222);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(39, 39, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLoginScreen adminDialog = new AdminLoginScreen();
				adminDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(60, 80, 135, 48);
		contentPane.add(btnNewButton);

		JButton btnUser = new JButton("USER");
		btnUser.setBackground(new Color(255, 255, 255));
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CustomerLoginScreen customerDialog = new CustomerLoginScreen();
				customerDialog.setVisible(true);
				dispose();

			}
		});
		btnUser.setBounds(240, 80, 135, 48);
		contentPane.add(btnUser);
	}

}
