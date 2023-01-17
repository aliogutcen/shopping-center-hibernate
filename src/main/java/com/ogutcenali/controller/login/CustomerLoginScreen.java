package com.ogutcenali.controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.controller.costumer.CustomerApp;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.service.CustomerService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;

public class CustomerLoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField passwordField;
	private CustomerService customerService = new CustomerService();

	public CustomerLoginScreen() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 317);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(39, 39, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(64, 117, 67, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(64, 153, 67, 14);
		contentPane.add(lblNewLabel_1);

		tf_username = new JTextField();
		tf_username.setBackground(new Color(255, 255, 255));
		tf_username.setBounds(141, 114, 201, 20);
		contentPane.add(tf_username);
		tf_username.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(141, 150, 201, 20);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean loginCustomerCheck = customerService.loginCheck(tf_username.getText(), passwordField.getText());

				if (loginCustomerCheck == true) {
					Customers customer = customerService.finByInfoForLogin(tf_username.getText());

					CustomerApp customerAppDialog = new CustomerApp(customer.getId());
					customerAppDialog.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Try again");
					removeFields();
				}

			}
		});
		btnNewButton.setBounds(141, 224, 79, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSignUp dialog = new CustomerSignUp();
				dialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(230, 224, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Forgot your password");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ForgetCustomerPassword passwordDialog = new ForgetCustomerPassword();
				passwordDialog.setVisible(true);
				dispose();

			}
		});
		lblNewLabel_2.setBounds(143, 190, 176, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CUSTOMER LOGIN ");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(111, 43, 191, 45);
		contentPane.add(lblNewLabel_3);
	}

	public void removeFields() {
		tf_username.setText("");
		passwordField.setText("");
	}
}
