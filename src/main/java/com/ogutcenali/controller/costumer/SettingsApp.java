package com.ogutcenali.controller.costumer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.entity.Customers;
import com.ogutcenali.service.CustomerService;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsApp extends JFrame {

	private JPanel contentPane;
	private Customers customers;
	private CustomerService customerService = new CustomerService();
	private JTextField tf_email;
	private JTextField tf_first;
	private JTextField tf_last;
	private JLabel lbl_identify;
	private JLabel lbl_id;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public SettingsApp(long id) {
		setTitle("Settings User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 509, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(48, 52, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Identify");
		lblNewLabel_1.setBounds(48, 88, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(48, 129, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Firstname");
		lblNewLabel_3.setBounds(48, 168, 73, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Lastname");
		lblNewLabel_4.setBounds(48, 204, 46, 14);
		contentPane.add(lblNewLabel_4);

		lbl_id = new JLabel("");
		lbl_id.setBounds(122, 52, 86, 14);
		contentPane.add(lbl_id);

		lbl_identify = new JLabel("");
		lbl_identify.setBounds(120, 88, 86, 14);
		contentPane.add(lbl_identify);

		tf_email = new JTextField();
		tf_email.setBounds(122, 126, 196, 20);
		contentPane.add(tf_email);
		tf_email.setColumns(10);

		tf_first = new JTextField();
		tf_first.setEditable(false);
		tf_first.setBounds(122, 165, 196, 20);
		contentPane.add(tf_first);
		tf_first.setColumns(10);

		tf_last = new JTextField();
		tf_last.setEditable(false);
		tf_last.setBounds(122, 201, 196, 20);
		contentPane.add(tf_last);
		tf_last.setColumns(10);

		btnNewButton = new JButton("Update E-mail");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				customerService.updateEmail(id, tf_email.getText());

			}
		});
		btnNewButton.setBounds(359, 125, 124, 23);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerApp customerDialog = new CustomerApp(id);
				customerDialog.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(394, 204, 89, 23);
		contentPane.add(btnNewButton_1);
		getInfo(id);
	}

	public void getInfo(long id) {
		Customers customer = customerService.findByIdForInfo(id);

		tf_first.setText(customer.getFirstName());
		tf_last.setText(customer.getLastName());
		tf_email.setText(customer.getEmail());
		lbl_id.setText(String.valueOf(customer.getId()));
		lbl_identify.setText(customer.getIdentity());
	}

}
