package com.ogutcenali.controller.admin;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ogutcenali.entity.Category;

import com.ogutcenali.entity.Product;
import com.ogutcenali.service.CategoryService;
import com.ogutcenali.service.ProductService;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ProductApp extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private ProductService productService = new ProductService();
	private CategoryService categoryService = new CategoryService();

	private JLabel lbl_foto2;
	ImageIcon myimage;
	private String imagePath = " ";
	private String imagePaths;
	private JButton btnadd;
	private byte[] image;
	private JTextField tf_productname;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField tf_stock;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_1;
	private JPanel panel;
	private JPanel panel_1;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JTextField tf_categoryId;
	private JButton btnNewButton_2;
	private JTextField tf_stockUpdate;
	private JTextField tf_id;
	private JPanel panel_3;
	private JButton btnNewButton_4;
	private JPanel panel_4;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JLabel lblNewLabel_5;
	private JTextField tf_price;

	public ImageIcon seticon(String m, byte[] image) {

		if (m != null) {

			myimage = new ImageIcon(m);
		} else {
			myimage = new ImageIcon(image);
		}

		Image img1 = myimage.getImage();
		Image img2 = img1.getScaledInstance(lbl_foto2.getWidth(), lbl_foto2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i = new ImageIcon(img2);
		return i;
	}

	public ProductApp() {
		setResizable(false);
		setTitle("Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1160, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnadd = new JButton("Add");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("user.dir"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("All Pic", "png", "jpg", "jpeg", "gif");
				fc.addChoosableFileFilter(filter);

				int a = fc.showSaveDialog(null);

				if (a == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();
					String p = f.getAbsolutePath();

					imagePaths = fc.getSelectedFile().getAbsolutePath();
					lbl_foto2.setIcon(seticon(p, image));

				}
			}
		});
		btnadd.setBounds(107, 412, 153, 23);
		contentPane.add(btnadd);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Product Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 11, 262, 256);
		contentPane.add(panel);
		panel.setLayout(null);

		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(10, 23, 238, 220);
		panel.add(lbl_foto2);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Add Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 274, 262, 228);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setBounds(10, 43, 45, 14);
		panel_1.add(lblNewLabel_1);

		lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 76, 68, 14);
		panel_1.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("Stock");
		lblNewLabel_2.setBounds(9, 107, 46, 14);
		panel_1.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Photo");
		lblNewLabel_3.setBounds(9, 141, 46, 14);
		panel_1.add(lblNewLabel_3);

		tf_productname = new JTextField();
		tf_productname.setBounds(83, 73, 151, 20);
		panel_1.add(tf_productname);
		tf_productname.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(83, 39, 151, 22);
		panel_1.add(comboBox);

		tf_stock = new JTextField();
		tf_stock.setBounds(83, 104, 151, 20);
		panel_1.add(tf_stock);
		tf_stock.setColumns(10);

		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setBounds(10, 194, 98, 20);
		panel_1.add(btnNewButton_1);

		lblNewLabel_5 = new JLabel("Price");
		lblNewLabel_5.setBounds(10, 169, 46, 14);
		panel_1.add(lblNewLabel_5);

		tf_price = new JTextField();
		tf_price.setBounds(83, 166, 151, 20);
		panel_1.add(tf_price);
		tf_price.setColumns(10);

		JButton btnNewButton_7 = new JButton("Update");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				productService.updateThisProduct(Integer.parseInt(tf_price.getText()), tf_productname.getText(),
						Long.parseLong(tf_id.getText()));
				
				JOptionPane.showMessageDialog(null, "UPDATE THIS PRODUCT");

			}
		});
		btnNewButton_7.setBounds(145, 193, 89, 23);
		panel_1.add(btnNewButton_7);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					FileInputStream fis = new FileInputStream(imagePaths);
					byte[] data = new byte[fis.available()];
					fis.read(data);

					Category category = categoryService.findByServiceId(String.valueOf(comboBox.getSelectedItem()));

					productService.createNewProduct(tf_productname.getText(), Integer.parseInt(tf_stock.getText()),
							data, category, Integer.parseInt(tf_price.getText()));

					removeTheField();

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(297, 11, 830, 352);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				int value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
				categoryInformation(value);
			}

		});

		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "id", "photo", "name", "price", "Stock", "CategoryName" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Stock Product", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(297, 374, 257, 121);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		tf_stockUpdate = new JTextField();
		tf_stockUpdate.setBounds(10, 43, 237, 20);
		panel_2.add(tf_stockUpdate);
		tf_stockUpdate.setColumns(10);

		JButton btnNewButton_3 = new JButton("StockUpdate");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productService.StockUpdateForId(Long.parseLong(tf_id.getText()),
						Integer.parseInt(tf_stockUpdate.getText()));

				removeTheField();
				getAllTable();

			}
		});
		btnNewButton_3.setBounds(10, 87, 237, 23);
		panel_2.add(btnNewButton_3);

		tf_id = new JTextField();
		tf_id.setEnabled(false);
		tf_id.setEditable(false);
		tf_id.setBounds(161, 11, 86, 20);
		panel_2.add(tf_id);
		tf_id.setColumns(10);

		lblNewLabel_4 = new JLabel("id");
		lblNewLabel_4.setBounds(133, 14, 26, 14);
		panel_2.add(lblNewLabel_4);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(564, 374, 363, 121);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnNewButton = new JButton("List All");
		btnNewButton.setBounds(10, 19, 332, 23);
		panel_3.add(btnNewButton);

		btnNewButton_2 = new JButton("Category ID LIST");
		btnNewButton_2.setBounds(10, 53, 208, 23);
		panel_3.add(btnNewButton_2);

		tf_categoryId = new JTextField();
		tf_categoryId.setBounds(228, 54, 114, 20);
		panel_3.add(tf_categoryId);
		tf_categoryId.setColumns(10);

		btnNewButton_4 = new JButton("Delete Product");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				productService.productDeleteWithById(Long.parseLong(tf_id.getText()));
				removeTheField();
				getAllTable();
			}
		});
		btnNewButton_4.setBounds(10, 87, 332, 23);
		panel_3.add(btnNewButton_4);

		panel_4 = new JPanel();
		panel_4.setBorder(
				new TitledBorder(null, "Prodcut Evaluate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(937, 374, 190, 85);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		btnNewButton_5 = new JButton("Evaluate");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductEvaluateApp productEvaluateDialog = new ProductEvaluateApp();
				productEvaluateDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setBounds(10, 15, 170, 59);
		panel_4.add(btnNewButton_5);

		btnNewButton_6 = new JButton("Back");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminApp adminDialog = new AdminApp();
				adminDialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton_6.setBounds(1036, 472, 89, 23);
		contentPane.add(btnNewButton_6);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Product> productWithIdCategory = productService
						.getListWithCategoryId(Long.parseLong(tf_categoryId.getText()));
				getAllTable(productWithIdCategory);

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getAllTable();
			}
		});
		comboGen();

	}

	public void categoryInformation(long value) {
		Product product = productService.FindInfoWithId(value);
		tf_productname.setText(product.getName());
		tf_stock.setText(String.valueOf(product.getStock()));
		comboBox.setSelectedItem(product.getCategory().getName());

		byte[] imageData = product.getImage();
		ImageIcon format = new ImageIcon(imageData);
		Image mm = format.getImage();
		Image img2 = mm.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(img2);
		lbl_foto2.setIcon(image);
		tf_price.setText(String.valueOf(product.getPrice()));
		tf_id.setText(String.valueOf(product.getId()));

	}

	protected void getAllTable(List<Product> productList2) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] column = new Object[6];

		for (int i = 0; i < productList2.size(); i++) {

			column[0] = productList2.get(i).getId();
			column[1] = productList2.get(i).getImage();
			column[2] = productList2.get(i).getName();
			column[3] = productList2.get(i).getPrice();
			column[4] = productList2.get(i).getStock();
			column[5] = productList2.get(i).getCategory().getName();
			model.addRow(column);

		}
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setRowHeight(100);

	}

	public void comboGen() {
		List<Category> categoryList = categoryService.getAllList();

		for (Category category : categoryList) {
			comboBox.addItem(category.getName());
		}

	}

	public void removeTheField() {
		tf_productname.setText("");
		tf_stock.setText("");
		lbl_foto2.setIcon(null);
		tf_id.setText("");
	}

	public void getAllTable() {
		List<Product> productList = productService.getAll();

		getAllTable(productList);
	}

	public class ImageRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			if (value != null) {
				lbl_foto2.setHorizontalAlignment(JLabel.CENTER);
				byte[] bytes = (byte[]) value;
				ImageIcon imageIcon = new ImageIcon(
						new ImageIcon(bytes).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));

				lbl_foto2.setIcon(imageIcon);

			}
			return lbl_foto2;
		}

	}
}
