package com.ogutcenali.controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.controller.admin.AdminApp;
import com.ogutcenali.service.AdminService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class AdminLoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField passwordField;
	private AdminService adminService = new AdminService();

	public AdminLoginScreen() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("AdminLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 364);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(39, 39, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_username = new JTextField();
		tf_username.setBounds(150, 107, 189, 20);
		contentPane.add(tf_username);
		tf_username.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password=");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(50, 156, 71, 14);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 153, 189, 20);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean checkAdmin = adminService.checkAdminLogin(tf_username.getText(), passwordField.getText());

				if (checkAdmin == true) {
					AdminApp adminDialog = new AdminApp();
					adminDialog.setVisible(true);
					dispose();
				} else {
					removeFields();

				}

			}
		});
		btnNewButton.setBounds(150, 204, 189, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminSignUp dialog = new AdminSignUp();
				dialog.setVisible(true);
				dispose();

			}
		});
		btnNewButton_1.setBounds(150, 251, 189, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Username=");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(50, 110, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("ADMIN LOGIN");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(166, 28, 173, 46);
		contentPane.add(lblNewLabel_2);
	}

	public void removeFields() {
		tf_username.setText("");
		passwordField.setText("");
	}
}
