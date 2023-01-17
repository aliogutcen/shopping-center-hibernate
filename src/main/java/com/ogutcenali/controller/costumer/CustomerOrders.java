package com.ogutcenali.controller.costumer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;
import com.ogutcenali.entity.ProductEvaluate;
import com.ogutcenali.service.CustomerService;
import com.ogutcenali.service.ProductEvaluateService;
import com.ogutcenali.service.ProductService;

import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JEditorPane;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class CustomerOrders extends JFrame {

	private JPanel contentPane;
	private Customers customer;
	private CustomerService customerService = new CustomerService();
	private ProductEvaluateService productEvaluateService = new ProductEvaluateService();
	private ProductService productService = new ProductService();
	private JTable table;
	private JPanel panel;
	private JEditorPane editorPane_comments;
	private JTextField tf_puan;
	private JLabel tf_id;

	/**
	 * Create the frame.
	 * 
	 * @param id
	 */
	public CustomerOrders(long id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1338, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 55, 711, 336);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				int value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
				getCommentsAboutProject(value, id);
				tf_id.setText(String.valueOf(value));
			}
		});
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"product_id", "product_name", "bought"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(116);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(755, 55, 534, 336);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(50, 35, 448, 202);
		panel.add(scrollPane_1);

		editorPane_comments = new JEditorPane();
		scrollPane_1.setViewportView(editorPane_comments);

		JLabel lblNewLabel = new JLabel("COMMENTS");
		lblNewLabel.setBounds(50, 11, 106, 14);
		panel.add(lblNewLabel);

		tf_puan = new JTextField();
		tf_puan.setBounds(156, 260, 185, 20);
		panel.add(tf_puan);
		tf_puan.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("How Many Stars");
		lblNewLabel_1.setBounds(56, 263, 99, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("(0-5)");
		lblNewLabel_2.setBounds(351, 263, 46, 14);
		panel.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("UPDATE/NEW COMMENTS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ProductEvaluate productEvaluate = productEvaluateService
						.getCommentsAboutProject(Integer.parseInt(tf_id.getText()), id);
				if (productEvaluate == null) {
					
					Customers customer = customerService.findByIdForComments(id);
					Product product = productService.FindInfoWithId(Integer.parseInt(tf_id.getText()));

					productEvaluateService
							.createCommentsAndStar(customer, product, editorPane_comments.getText(), Integer.parseInt(tf_puan.getText()));

				} else {
					editorPane_comments.setText(productEvaluate.getComment());
					productEvaluateService.updateCommentsAndStar(productEvaluate, editorPane_comments.getText(),
							Integer.parseInt(tf_puan.getText()));

				}

			}
		});
		btnNewButton.setBounds(50, 291, 448, 23);
		panel.add(btnNewButton);

		tf_id = new JLabel("");
		tf_id.setBounds(452, 10, 46, 14);
		panel.add(tf_id);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerApp dialog = new CustomerApp(id);
				dialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(1223, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		getAllProductCustomers(id);
	}

	protected void getCommentsAboutProject(int value, long id) {
		ProductEvaluate productEvaluate = productEvaluateService.getCommentsAboutProject(value, id);

		if (productEvaluate == null) {
			JOptionPane.showMessageDialog(null, "There are no comments about the product!!");
			editorPane_comments.setText("");
		} else {
			editorPane_comments.setText(productEvaluate.getComment());
			tf_puan.setText(String.valueOf(productEvaluate.getPuan()));
		}

	}

	public void getAllProductCustomers(long id) {
		Customers customer = customerService.getAllOrders(id);
		List<Product> productListForCustomer = customer.getProduct();
		HashMap<Product, Integer> productCount = new HashMap<>();

		for (int i = 0; i < productListForCustomer.size(); i++) {

			if (productCount.containsKey(productListForCustomer.get(i))) {
				productCount.replace(productListForCustomer.get(i),
						productCount.get(productListForCustomer.get(i)) + 1);

			} else {
				productCount.put(productListForCustomer.get(i), 1);
			}
		}

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[3];
		model.setRowCount(0);
		for (Map.Entry<Product, Integer> entry : productCount.entrySet()) {
			column[0] = entry.getKey().getId();
			column[1] = entry.getKey().getName();
			column[2] = entry.getValue();
			model.addRow(column);
		}

	}
}
