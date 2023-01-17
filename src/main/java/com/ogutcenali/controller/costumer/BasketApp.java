package com.ogutcenali.controller.costumer;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ogutcenali.controller.costumer.CustomerApp.ImageRenderer;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;
import com.ogutcenali.service.CustomerService;
import com.ogutcenali.service.ProductService;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BasketApp extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lbl_foto2;
	private JTextField tf_totalamount;
	private ProductService productService = new ProductService();
	private CustomerService customerService = new CustomerService();

	public BasketApp(long id, HashMap<Product, Integer> basketToProduct) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1123, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 658, 172);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				int value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "product_id", "product_photo", "price", "count", "total" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(3).setPreferredWidth(118);
		scrollPane.setViewportView(table);

		lbl_foto2 = new JLabel("");
		lbl_foto2.setBounds(76, 592, 46, 14);
		contentPane.add(lbl_foto2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(678, 59, 419, 172);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Basket Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(146, 36, 141, 59);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Total Amount");
		lblNewLabel_1.setBounds(28, 97, 77, 14);
		panel.add(lblNewLabel_1);

		tf_totalamount = new JTextField();
		tf_totalamount.setBounds(131, 94, 278, 20);
		panel.add(tf_totalamount);
		tf_totalamount.setColumns(10);

		JButton btnNewButton = new JButton("Buy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (Map.Entry<Product, Integer> entry : basketToProduct.entrySet()) {
					Product key = entry.getKey();
					Integer val = entry.getValue();

					Customers customer = customerService.updateProducts(id, entry.getKey());

					productService.StockUpdateForBasket(entry.getKey().getId(), entry.getValue());
					
				}
				
				JOptionPane.showMessageDialog(null, "congrats");
				CustomerApp dialog = new CustomerApp(id);
				dialog.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(28, 122, 381, 23);
		panel.add(btnNewButton);
		getBasketList(basketToProduct);
		informationBasket(basketToProduct);

	}

	public void getBasketList(HashMap<Product, Integer> basketToProduct) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[5];
		model.setRowCount(0);
		for (Map.Entry<Product, Integer> entry : basketToProduct.entrySet()) {
			column[0] = entry.getKey().getId();
			column[1] = entry.getKey().getImage();
			column[2] = entry.getKey().getPrice();
			column[3] = entry.getValue();

			int total = entry.getKey().getPrice() * entry.getValue();
			column[4] = total;
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

	public void informationBasket(HashMap<Product, Integer> basketToProduct) {
		int count = 0;
		for (Map.Entry<Product, Integer> entry : basketToProduct.entrySet()) {

			count = count + (entry.getKey().getPrice() * entry.getValue());
		}
		tf_totalamount.setText(String.valueOf(count));

	}

}
