package com.ogutcenali.controller.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminApp extends JFrame {

	private JPanel contentPane;

	public AdminApp() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Category");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CategoryApp categoryAppDialog = new CategoryApp();
				categoryAppDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 52, 130, 48);
		contentPane.add(btnNewButton);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductApp productAppDialog = new ProductApp();
				productAppDialog.setVisible(true);
				dispose();
			}
		});
		btnProduct.setBounds(170, 52, 130, 48);
		contentPane.add(btnProduct);
		
		JButton btnCostumer = new JButton("Costumers");
		btnCostumer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CostumersInfoForAdmin costumerInfoDialog = new CostumersInfoForAdmin();
				costumerInfoDialog.setVisible(true);
				dispose();
			}
		});
		btnCostumer.setBounds(329, 52, 130, 48);
		contentPane.add(btnCostumer);
	}
}
