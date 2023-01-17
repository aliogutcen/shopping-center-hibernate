package com.ogutcenali.controller.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.service.CustomerService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField tf_identify;
	private JTextField tf_Firstname;
	private JTextField tf_Lastname;
	private JPasswordField passwordField;
	private JTextField tf_email;
	private JLabel lbl_error;
	JComboBox cb_mail;
	private CustomerService customerService = new CustomerService();

	public CustomerSignUp() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 519);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(39, 39, 39));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(163, 56, 85, 32);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Identify");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(59, 131, 56, 14);
		contentPane.add(lblNewLabel_1);

		tf_identify = new JTextField();
		tf_identify.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				String phoneNumber = tf_identify.getText();
				int length = phoneNumber.length();
				char c = e.getKeyChar();

				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {

					if (length < 11) {

						tf_identify.setEditable(true);
					} else {
						tf_identify.setEditable(false);

					}

				} else {

					if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
							|| e.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
						tf_identify.setEditable(true);
					} else {
						tf_identify.setEditable(false);
						JOptionPane.showMessageDialog(null, "Please 0-9");
						tf_identify.setText("");
						tf_identify.setEditable(true);
					}
				}
			}
		});
		tf_identify.setBounds(123, 128, 188, 20);
		contentPane.add(tf_identify);
		tf_identify.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Firstname");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(59, 176, 66, 14);
		contentPane.add(lblNewLabel_2);

		tf_Firstname = new JTextField();
		tf_Firstname.setColumns(10);
		tf_Firstname.setBounds(123, 173, 188, 20);
		contentPane.add(tf_Firstname);

		JLabel lblNewLabel_2_1 = new JLabel("Lastname");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(59, 219, 66, 14);
		contentPane.add(lblNewLabel_2_1);

		tf_Lastname = new JTextField();
		tf_Lastname.setColumns(10);
		tf_Lastname.setBounds(123, 216, 188, 20);
		contentPane.add(tf_Lastname);

		passwordField = new JPasswordField();
		passwordField.setBounds(123, 262, 188, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(59, 265, 56, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(59, 315, 56, 14);
		contentPane.add(lblNewLabel_4);

		tf_email = new JTextField();
		tf_email.setBounds(123, 312, 93, 20);
		contentPane.add(tf_email);
		tf_email.setColumns(10);

		cb_mail = new JComboBox();
		cb_mail.setBackground(new Color(255, 255, 255));
		cb_mail.setModel(
				new DefaultComboBoxModel(new String[] { "@gmail.com", "@hotmail.com", "@indeed.com", "@shoop.com" }));
		cb_mail.setBounds(218, 311, 93, 22);
		contentPane.add(cb_mail);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean createNewCustomer = customerService.newCustomerCheck(tf_identify.getText(),
						tf_Firstname.getText(), tf_Lastname.getText(), passwordField.getText(), tf_email.getText(),
						String.valueOf(cb_mail.getSelectedItem()));

				if (createNewCustomer == true) {
					JOptionPane.showMessageDialog(null, "Congrats you're welcome");
					dispose();
					CustomerLoginScreen customerLoginDialog = new CustomerLoginScreen();
					customerLoginDialog.setVisible(true);
				} else {

					tf_identify.setEditable(true);
				}

			}
		});
		btnNewButton.setBounds(127, 363, 184, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Geri Dön");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLoginScreen dialog = new CustomerLoginScreen();
				dialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(282, 446, 89, 23);
		contentPane.add(btnNewButton_1);

		lbl_error = new JLabel("");
		lbl_error.setBounds(123, 338, 188, 14);
		contentPane.add(lbl_error);
	}

	public void removeFields() {
		tf_email.setText("");
		tf_Firstname.setText("");
		tf_Lastname.setText("");
		tf_identify.setText("");
		passwordField.setText("");
	}
}
