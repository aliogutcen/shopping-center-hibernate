package com.ogutcenali.controller.costumer;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.controller.admin.ProductApp.ImageRenderer;
import com.ogutcenali.entity.Category;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;
import com.ogutcenali.entity.ProductEvaluate;
import com.ogutcenali.service.CategoryService;
import com.ogutcenali.service.CustomerService;
import com.ogutcenali.service.ProductEvaluateService;
import com.ogutcenali.service.ProductService;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class CustomerApp extends JFrame {

	protected static final int Product = 0;
	protected static final int List = 0;
	private JPanel contentPane;
	private JLabel lbl_hosgeldin;
	private JComboBox comboBox;
	private Customers customers;
	private CustomerService customerService = new CustomerService();
	private CategoryService categoryService = new CategoryService();
	private JTable table;
	private JScrollPane scrollPane;
	private ProductService productService = new ProductService();
	private byte[] image;
	private ProductEvaluateService productEvaluateService = new ProductEvaluateService();
	ImageIcon myimage;
	private String imagePath = " ";
	private String imagePaths;
	private JLabel lbl_stock;
	private JLabel lbl_name;
	private JLabel lbl_foto2;
	private JLabel lbl_id;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_7;
	private JButton btnNewButton_4;

	private JTable table_1;
	private JLabel lbl_basketscount;
	private HashMap<Product, Integer> basketToProduct = new HashMap<>();

	public CustomerApp(long id) {
		setResizable(false);
		setTitle("Customer App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1410, 662);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl_hosgeldin = new JLabel("");
		lbl_hosgeldin.setBounds(981, 21, 160, 14);
		contentPane.add(lbl_hosgeldin);
		lbl_hosgeldin.setText(getName());
	//	getInfo(id);
		JButton btnNewButton = new JButton("Settings");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsApp settingDialog = new SettingsApp(id);
				settingDialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(1272, 17, 116, 23);
		contentPane.add(btnNewButton);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Product> productList = productService.getAll(String.valueOf(comboBox.getSelectedItem()));

				makeListForTable(productList);
			}
		});
		comboBox.setBounds(23, 51, 253, 22);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Category");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(123, 20, 63, 14);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 105, 1062, 485);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				int value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
				categoryInformation(value);
				getCommentsAboutThisProject(value);

			}
		});

		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "photo", "name", "price", "stock"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		lbl_name = new JLabel("");
		lbl_name.setBounds(878, 383, 116, 14);
		contentPane.add(lbl_name);

		lbl_stock = new JLabel("");
		lbl_stock.setBounds(878, 451, 46, 14);
		contentPane.add(lbl_stock);

		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(787, 105, 15, 17);
		contentPane.add(lbl_foto2);

		JButton btnNewButton_1 = new JButton("Add Basket");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int customerbought = Integer.parseInt(JOptionPane.showInputDialog("How many do you want to buy?"));

				Product product = productService.FindInfoWithId(Integer.parseInt(lbl_id.getText()));

				if (basketToProduct.containsKey(product)) {
					basketToProduct.replace(product,
							basketToProduct.get(basketToProduct.get(product)) + customerbought);

				} else {

					basketToProduct.put(product, customerbought);

				}

				removeTextFields();

				lbl_basketscount.setText(String.valueOf(basketToProduct.size()));

			}
		});
		btnNewButton_1.setBounds(974, 589, 111, 23);
		contentPane.add(btnNewButton_1);

		lbl_id = new JLabel("");
		lbl_id.setForeground(Color.WHITE);
		lbl_id.setBounds(878, 615, 0, 0);
		contentPane.add(lbl_id);

		lblNewLabel_6 = new JLabel("Stock Quantity Less Than 10");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(435, 20, 206, 14);
		contentPane.add(lblNewLabel_6);

		btnNewButton_2 = new JButton("Bring Products");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "id", "photo", "name", "price", "stock" }));

				List<Product> productLessThanTen = productService.getAllMoreLessTen();
				makeListForTable(productLessThanTen);

			}
		});
		btnNewButton_2.setBounds(348, 51, 293, 23);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("My Orders");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerOrders customerOrdersDialog = new CustomerOrders(id);
				customerOrdersDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(1151, 17, 111, 23);
		contentPane.add(btnNewButton_3);

		lblNewLabel_7 = new JLabel("Highest Star Rating");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(735, 20, 138, 14);
		contentPane.add(lblNewLabel_7);

		btnNewButton_4 = new JButton("Highest Star Products");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				table.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "id", "photo", "name", "price", "stock", "stars" }));

				HashMap<ProductEvaluate, Long> highestStar = productEvaluateService.highestStarRating();

				makeATableForStar(highestStar);

			}
		});
		btnNewButton_4.setBounds(682, 51, 231, 23);
		contentPane.add(btnNewButton_4);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1097, 105, 293, 486);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setSelectionForeground(Color.WHITE);
		table_1.setSelectionBackground(Color.DARK_GRAY);
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "comments", "star", "users" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		scrollPane_1.setViewportView(table_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				BasketApp basketAppDialog = new BasketApp(id, basketToProduct);
				basketAppDialog.setVisible(true);
				dispose();

			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(CustomerApp.class.getResource("/com/ogutcenali/assets/sepet.png")));
		lblNewLabel_1.setBounds(1285, 66, 46, 28);
		contentPane.add(lblNewLabel_1);

		lbl_basketscount = new JLabel("");
		lbl_basketscount.setBounds(1338, 66, 46, 28);
		contentPane.add(lbl_basketscount);
		comboGen();

	}

	protected void getCommentsAboutThisProject(int value) {
		List<ProductEvaluate> commentAboutProduct = productEvaluateService.getAllCommentsThisProduct(value);
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);
		Object[] columns = new Object[3];
		for (int i = 0; i < commentAboutProduct.size(); i++) {
			columns[0] = commentAboutProduct.get(i).getComment();
			columns[1] = commentAboutProduct.get(i).getPuan();
			String newEmailType = commentAboutProduct.get(i).getCustomer().getEmail().substring(0, 3);
			String userGui = newEmailType + "***";
			columns[2] = userGui;

			model.addRow(columns);
		}
	}

	protected void makeATableForStar(HashMap<ProductEvaluate, Long> highestStar) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] columns = new Object[6];
		for (Map.Entry<ProductEvaluate, Long> entry : highestStar.entrySet()) {
			if (entry.getValue() > 4) {

				columns[0] = entry.getKey().getProduct().getId();
				columns[1] = entry.getKey().getProduct().getImage();
				columns[2] = entry.getKey().getProduct().getName();
				columns[3] = entry.getKey().getProduct().getPrice();
				columns[4] = entry.getKey().getProduct().getStock();
				columns[5] = entry.getValue();
				model.addRow(columns);
			}
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setRowHeight(100);
	}

	protected void makeListForTable(List<Product> productList) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[5];
		model.setRowCount(0);
		for (int i = 0; i < productList.size(); i++) {
			column[0] = productList.get(i).getId();
			column[1] = productList.get(i).getImage();
			column[2] = productList.get(i).getName();
			column[3] = productList.get(i).getPrice();
			column[4] = productList.get(i).getStock();
			model.addRow(column);
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setRowHeight(100);

	}

	class ImageRenderer extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			if (value != null) {
				lbl_foto2.setHorizontalAlignment(JLabel.CENTER);
				byte[] bytes = (byte[]) value;
				ImageIcon imageIcon = new ImageIcon(
						new ImageIcon(bytes).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

				lbl_foto2.setIcon(imageIcon);

			}
			return lbl_foto2;
		}

	}

	public void getInfo(long id) {

		Customers customer = customerService.findByIdForInfo(id);
		lbl_hosgeldin.setText("Hoşgeldin " + customer.getFirstName() + " " + customer.getLastName());
	}

	public void comboGen() {
		List<Category> categoryList = categoryService.getAllList();

		for (Category category : categoryList) {
			comboBox.addItem(category.getName());
		}

	}

	public void categoryInformation(long value) {
		Product product = productService.getAllInfoWithId(value);

		lbl_id.setText(String.valueOf(product.getId()));

	}

	public void removeTextFields() {
		lbl_name.setText("");

		lbl_stock.setText("");

		lbl_id.setText("");

	}
}
