package com.ogutcenali.controller.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.ogutcenali.entity.Category;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;
import com.ogutcenali.entity.ProductEvaluate;
import com.ogutcenali.service.CategoryService;
import com.ogutcenali.service.CustomerService;
import com.ogutcenali.service.ProductEvaluateService;
import com.ogutcenali.service.ProductService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class ProductEvaluateApp extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private ProductEvaluateService productEvaService = new ProductEvaluateService();
	private CustomerService costumerService = new CustomerService();
	private CategoryService categoryService = new CategoryService();
	private ProductService productService = new ProductService();

	private JTextField tf_customerId;
	private JTextField tf_identify;
	private JComboBox comboBox;


	public ProductEvaluateApp() {
		setTitle("Product Evaluate");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1334, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 1295, 310);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "product_id", "customer_id", "comment", "puan" }));
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("GET ALL COMMENTS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<ProductEvaluate> productEva = productEvaService.getAllComments();
				makeTable(productEva);
			}
		});

		btnNewButton.setBounds(10, 349, 264, 23);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Comments For Product Id", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 383, 264, 81);
		contentPane.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<ProductEvaluate> productEvoWithProductId = productEvaService
						.getListWithProductId(Integer.parseInt(comboBox.getSelectedItem().toString()));
				makeTable(productEvoWithProductId);

			}
		});

		comboBox.setBounds(10, 37, 244, 22);
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Customer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(284, 347, 774, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		tf_customerId = new JTextField();
		tf_customerId.setBounds(115, 29, 245, 20);
		panel_1.add(tf_customerId);
		tf_customerId.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer Id");
		lblNewLabel.setBounds(10, 32, 114, 14);
		panel_1.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("GET ALL COMMENTS FOR ID");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Customers customer = costumerService.findByIdForComments(Long.parseLong(tf_customerId.getText()));

				if (customer != null) {

					List<ProductEvaluate> productEva = productEvaService.getAllCommentsWithCustomerId(customer);

					makeTable(productEva);
				}

				else {

					tf_identify.setText("");
				}

			}
		});

		comboGen();
		btnNewButton_1.setBounds(10, 57, 350, 23);
		panel_1.add(btnNewButton_1);

		tf_identify = new JTextField();
		tf_identify.setBounds(469, 29, 295, 20);
		panel_1.add(tf_identify);
		tf_identify.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Identify");
		lblNewLabel_1.setBounds(414, 32, 76, 14);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_2 = new JButton("GET ALL COMMENTS FOR IDENTIFY");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Customers customer = costumerService.findByIdForComments(tf_identify.getText());

				if (customer != null) {

					List<ProductEvaluate> productEva = productEvaService.getAllCommentsWithCustomerId(customer);
					makeTable(productEva);
				}

				else {

					tf_customerId.setText("");
				}

			}
		});
		btnNewButton_2.setBounds(414, 57, 350, 23);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductApp productDialog = new ProductApp();
				productDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(1219, 441, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(1068, 347, 240, 91);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSlider slider = new JSlider();
		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				
				List<ProductEvaluate> productEvaluatesPuan = productEvaService.getAllWithPuan(slider.getValue());
				makeTable(productEvaluatesPuan);
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setValue(0);
		slider.setPaintTicks(true);
		slider.setMaximum(5);
		slider.setBounds(10, 21, 220, 47);
		panel_2.add(slider);
	}

	public void makeTable(List<ProductEvaluate> productEva) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] column = new Object[5];
		model.setRowCount(0);
		for (int i = 0; i < productEva.size(); i++) {
			column[0] = productEva.get(i).getId();
			column[1] = productEva.get(i).getProduct().getId();
			column[2] = productEva.get(i).getCustomer().getId();
			column[3] = productEva.get(i).getComment();
			column[4] = productEva.get(i).getPuan();
			model.addRow(column);
		}

	}

	public void comboGen() {
		List<Product> productList = productService.getAll();

		for (Product product : productList) {
			comboBox.addItem(product.getId());
		}

	}
}
