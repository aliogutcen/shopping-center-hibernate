package com.ogutcenali.controller.login;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.service.AdminService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField tf_firstname;
	private JTextField tf_lastname;
	private JPasswordField passwordField;
	private JTextField tf_email;

	private AdminService adminService = new AdminService();

	public AdminSignUp() {
		setTitle("Admin Register");
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 409);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(39, 39, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(163, 56, 85, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Firstname");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(59, 112, 66, 14);
		contentPane.add(lblNewLabel_2);

		tf_firstname = new JTextField();
		tf_firstname.setColumns(10);
		tf_firstname.setBounds(123, 109, 188, 20);
		contentPane.add(tf_firstname);

		JLabel lblNewLabel_2_1 = new JLabel("Lastname");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(59, 153, 66, 14);
		contentPane.add(lblNewLabel_2_1);

		tf_lastname = new JTextField();
		tf_lastname.setColumns(10);
		tf_lastname.setBounds(123, 150, 188, 20);
		contentPane.add(tf_lastname);

		passwordField = new JPasswordField();
		passwordField.setBounds(123, 191, 188, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(59, 194, 56, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(59, 236, 56, 14);
		contentPane.add(lblNewLabel_4);

		tf_email = new JTextField();
		tf_email.setBounds(123, 233, 93, 20);
		contentPane.add(tf_email);
		tf_email.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "@gmail.com", "@hotmail.com", "@indeed.com", "@shoop.com" }));
		comboBox.setBounds(218, 232, 93, 22);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				adminService.createAdmin(tf_firstname.getText(), tf_lastname.getText(), passwordField.getText(),
						tf_email.getText(), String.valueOf(comboBox.getSelectedItem()));
				deleteFields();
			}
		});
		btnNewButton.setBounds(127, 278, 184, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Back Login");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLoginScreen dialog = new AdminLoginScreen();
				dialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(292, 347, 89, 23);
		contentPane.add(btnNewButton_1);
	}

	public void deleteFields() {
		tf_email.setText("");
		tf_firstname.setText("");
		tf_lastname.setText("");

		passwordField.setText("");
	}

}
