package com.ogutcenali.controller.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ogutcenali.entity.Customers;
import com.ogutcenali.service.CustomerService;
import com.ogutcenali.service.ProductEvaluateService;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;

public class CostumersInfoForAdmin extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_id;
	private JTextField tf_firstname;

	private CustomerService customerService = new CustomerService();
	private ProductEvaluateService productEvoService = new ProductEvaluateService();

	private JTextField tf_lastname;
	private JTextField tf_email;
	private JTextField tf_product;
	private JTextField tf_comments;
	private JTextField tf_puanforcomments;


	public CostumersInfoForAdmin() {
		setResizable(false);
		setTitle("Costumers Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1463, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(446, 11, 1001, 564);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				int value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
				customerInformation(value);
			}
		});

		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "email", "firstname", "lastname", "product count", "count comment" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 426, 452);
		contentPane.add(panel);
		panel.setLayout(null);

		tf_id = new JTextField();
		tf_id.setBounds(80, 117, 329, 20);
		panel.add(tf_id);
		tf_id.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(9, 120, 61, 14);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("GET ALL INFO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Customers customers = customerService.findByIdForInfo(Long.parseLong(tf_id.getText()));

				if (customers != null) {
					tf_id.setText(String.valueOf(customers.getId()));
					tf_firstname.setText(customers.getFirstName());
					tf_lastname.setText(customers.getLastName());
					tf_email.setText(customers.getEmail());
					tf_comments.setText(String.valueOf(customers.getProductEvaluate().size()));
					tf_product.setText(String.valueOf(customers.getProduct().size()));

				} else {
					tf_id.setText("");
				}

			}
		});
		btnNewButton.setBounds(10, 384, 399, 23);
		panel.add(btnNewButton);

		tf_firstname = new JTextField();
		tf_firstname.setBounds(80, 163, 329, 20);
		panel.add(tf_firstname);
		tf_firstname.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("FirstName");
		lblNewLabel_1.setBounds(10, 166, 115, 14);
		panel.add(lblNewLabel_1);

		tf_lastname = new JTextField();
		tf_lastname.setColumns(10);
		tf_lastname.setBounds(80, 214, 329, 20);
		panel.add(tf_lastname);

		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(80, 260, 329, 20);
		panel.add(tf_email);

		tf_product = new JTextField();
		tf_product.setColumns(10);
		tf_product.setBounds(80, 305, 329, 20);
		panel.add(tf_product);

		tf_comments = new JTextField();
		tf_comments.setColumns(10);
		tf_comments.setBounds(80, 353, 329, 20);
		panel.add(tf_comments);

		JLabel lblNewLabel_2 = new JLabel("Lastname");
		lblNewLabel_2.setBounds(9, 217, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(9, 263, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Product Count");
		lblNewLabel_4.setBounds(9, 308, 46, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Comments");
		lblNewLabel_5.setBounds(10, 356, 46, 14);
		panel.add(lblNewLabel_5);

		JButton btnNewButton_2 = new JButton("X");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				removeTextFields();

			}
		});
		btnNewButton_2.setBounds(327, 418, 89, 23);
		panel.add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("GET ALL COSTUMER");
		btnNewButton_1.setBounds(10, 49, 399, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Customers> customersListAll = customerService.getAllCustomers();

				makeListTable(customersListAll);

			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 469, 426, 106);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("AVG PUAN FOR COMMENTS");
		lblNewLabel_6.setBounds(10, 46, 133, 14);
		panel_1.add(lblNewLabel_6);

		tf_puanforcomments = new JTextField();
		tf_puanforcomments.setBounds(167, 43, 249, 20);
		panel_1.add(tf_puanforcomments);
		tf_puanforcomments.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("AVG PUAN");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				long avg = productEvoService.CommentsPuanWithCustomerId(Integer.parseInt(tf_id.getText()));
				tf_puanforcomments.setText(String.valueOf(avg));
				
			}
		});
		btnNewButton_3.setBounds(10, 72, 406, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminApp adminDialog = new AdminApp();
				adminDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(1358, 586, 89, 23);
		contentPane.add(btnNewButton_4);
	}

	protected void customerInformation(int value) {
		Customers customers = customerService.findByIdForInfo(value);

		tf_id.setText(String.valueOf(customers.getId()));
		tf_firstname.setText(customers.getFirstName());
		tf_lastname.setText(customers.getLastName());
		tf_email.setText(customers.getEmail());
		tf_comments.setText(String.valueOf(customers.getProductEvaluate().size()));
		tf_product.setText(String.valueOf(customers.getProduct().size()));

	}

	protected void removeTextFields() {
		tf_id.setText("");
		tf_firstname.setText("");
		tf_lastname.setText("");
		tf_email.setText("");
		tf_comments.setText("");
		tf_product.setText("");

	}

	protected void makeListTable(List<Customers> customersListAll) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Object[] column = new Object[6];
		model.setRowCount(0);
		for (int i = 0; i < customersListAll.size(); i++) {

			column[0] = customersListAll.get(i).getId();
			column[1] = customersListAll.get(i).getEmail();
			column[2] = customersListAll.get(i).getFirstName();
			column[3] = customersListAll.get(i).getLastName();
			column[4] = customersListAll.get(i).getProduct().size();
			column[5] = customersListAll.get(i).getProductEvaluate().size();
			model.addRow(column);
		}

	}

	public void avgCommentsPuan(int value) {

		long avg = productEvoService.CommentsPuanWithCustomerId(value);
		tf_puanforcomments.setText(String.valueOf(avg));
	}
}
