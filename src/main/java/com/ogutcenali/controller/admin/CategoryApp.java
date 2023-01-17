package com.ogutcenali.controller.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ogutcenali.entity.Category;
import com.ogutcenali.service.CategoryService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryApp extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_id;
	private JTextField tf_name;

	private CategoryService categoryService = new CategoryService();

	public CategoryApp() {
		setResizable(false);
		setTitle("Category Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(344, 32, 646, 238);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selectRow = table.getSelectedRow();
				TableModel model = table.getModel();
				long value = Integer.parseInt(model.getValueAt(selectRow, 0).toString());
				categoryInformation(value);
			}
		});
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "id", "category_name", "product_count" }));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(32, 32, 271, 238);

		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("Id=");
		lblId.setBounds(10, 36, 77, 14);
		panel.add(lblId);

		tf_id = new JTextField();
		tf_id.setEditable(false);
		tf_id.setBounds(66, 33, 182, 20);
		panel.add(tf_id);
		tf_id.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 88, 46, 14);
		panel.add(lblNewLabel);

		tf_name = new JTextField();
		tf_name.setBounds(66, 85, 182, 20);
		panel.add(tf_name);
		tf_name.setColumns(10);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean checkSave = categoryService.saveNewCategory(tf_name.getText());
				if (checkSave == true) {
					JOptionPane.showMessageDialog(null, "Category Add");
					tf_name.setText("");
					makeTable();
				} else {
					JOptionPane.showMessageDialog(null, "Category name has");
					tf_name.setText("");
					makeTable();
				}

			}
		});
		btnNewButton.setBounds(10, 128, 114, 23);
		panel.add(btnNewButton);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				categoryService.updateWithId(Long.parseLong(tf_id.getText()), tf_name.getText());
				makeTable();
			}
		});
		btnUpdate.setBounds(134, 128, 114, 23);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryService.deleteCategory(Long.parseLong(tf_id.getText()));
				makeTable();
			}
		});
		btnDelete.setBounds(10, 171, 114, 23);
		panel.add(btnDelete);

		JButton btnListAll = new JButton("List All");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				makeTable();

			}
		});
		btnListAll.setBounds(134, 171, 114, 23);
		panel.add(btnListAll);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(134, 204, 114, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminApp adminAppDialog = new AdminApp();
				adminAppDialog.setVisible(true);
				dispose();
			}
		});
	}

	protected void categoryInformation(long value) {

		Category category = categoryService.findById(value);

		tf_id.setText(String.valueOf(category.getId()));
		tf_name.setText(category.getName());

	}

	protected void makeTable() {
		List<Category> categoryList = categoryService.getAllList();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[3];
		model.setRowCount(0);
		for (int i = 0; i < categoryList.size(); i++) {
			column[0] = categoryList.get(i).getId();
			column[1] = categoryList.get(i).getName();
			column[2] = categoryList.get(i).getProduct().size();

			model.addRow(column);
		}

	}
}
