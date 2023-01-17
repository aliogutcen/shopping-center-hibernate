package com.ogutcenali.controller.costumer;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ogutcenali.entity.ProductEvaluate;
import com.ogutcenali.service.CustomerService;
import com.ogutcenali.service.ProductEvaluateService;

import javax.swing.JScrollPane;

public class AllEvaluate extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private CustomerService customerService= new CustomerService();
	private ProductEvaluateService productEvaluateService = new ProductEvaluateService();


	/**
	 * Create the frame.
	 * @param id 
	 */
	public AllEvaluate(long id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 994, 682);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"product_name", "comments", "puan"
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
		table.getColumnModel().getColumn(2).setResizable(false);
		getAllCommentsAndPuan(id);
	}
	
	
	public void getAllCommentsAndPuan(long id) {
		
		List<ProductEvaluate> productEvaluate = productEvaluateService.getAllComments(id);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[4];
		model.setRowCount(0);
		for (int i = 0; i < productEvaluate.size(); i++) {
			column[0] = productEvaluate.get(i).getProduct().getName();
			column[1] = productEvaluate.get(i).getComment();
			column[2] = productEvaluate.get(i).getPuan();
			model.addRow(column);
		}
		
		
	}

}
