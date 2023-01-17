package com.ogutcenali.controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.service.CustomerService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ForgetCustomerPassword extends JFrame {

	private JPanel contentPane;
	private JTextField tf_identify;
	private JPasswordField passwordField;
	private CustomerService customerService = new CustomerService();
	
	public ForgetCustomerPassword() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Forgot Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 238);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(39, 39, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identify");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(73, 70, 47, 14);
		contentPane.add(lblNewLabel);
		
		tf_identify = new JTextField();
		tf_identify.setBounds(130, 67, 178, 20);
		contentPane.add(tf_identify);
		tf_identify.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(73, 111, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 108, 178, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean updateCustomerPassword=	customerService.updatePassword(tf_identify.getText(),passwordField.getText());
				
				if(updateCustomerPassword==true) {
					JOptionPane.showMessageDialog(null, tf_identify.getText()+" -> password update");
					removeFields();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Try again");
					removeFields();
				}
				
			}
		});
		btnNewButton.setBounds(130, 139, 178, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("back");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLoginScreen customerDialog = new CustomerLoginScreen();
				customerDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(336, 153, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public void removeFields() {
		tf_identify.setText("");
		passwordField.setText("");
	}
	

}
