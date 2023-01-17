package com.ogutcenali.service;

import java.util.List;

import javax.swing.JOptionPane;

import com.ogutcenali.dao.CustomerDao;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;

public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();

	public boolean updatePassword(String identify, String pass) {

		Customers customer = customerDao.listAll().stream().filter((c) -> c.getIdentity().equals(identify)).findAny()
				.orElse(null);

		if (customer != null) {
			customerDao.update(customer);
			return true;

		}

		return false;

	}

	public boolean loginCheck(String email, String pass) {

		Customers customer = customerDao.listAll().stream()
				.filter((c) -> c.getEmail().equals(email) && c.getPassword().equals(pass)).findAny().orElse(null);

		if (customer != null) {
			return true;
		}

		return false;
	}

	public Customers findByIdForComments(long parseLong) {
		Customers customer = customerDao.findById(parseLong);

		if (customer == null) {
			JOptionPane.showMessageDialog(null, "we dont have this id");
			return null;
		}

		return customer;
	}

	public Customers findByIdForComments(String identify) {
		Customers customer = customerDao.listAll().stream().filter((c) -> c.getIdentity().equals(identify)).findAny()
				.orElse(null);

		if (customer != null) {

			return customer;

		}

		return null;
	}

	public List<Customers> getAllCustomers() {

		return customerDao.listAll();
	}

	public Customers findByIdForInfo(long parseLong) {

		Customers customer = customerDao.listAll().stream().filter((c) -> c.getId() == parseLong).findAny()
				.orElse(null);

		if (customer == null) {
			JOptionPane.showMessageDialog(null, "we dont have this id for costumers!");
			return null;
		}

		else {
			return customer;
		}

	}

	public Customers finByInfoForLogin(String text) {
		Customers costumer = customerDao.listAll().stream().filter((c) -> c.getEmail().equals(text)).findAny()
				.orElse(null);
		return costumer;
	}

	public void updateEmail(long id, String text) {
		Customers customer = customerDao.findById(id);
		customer.setEmail(text);
		customerDao.update(customer);

	}

	public void addBasket(Product product, long id) {
		Customers customer = customerDao.findById(id);
		customer.getProduct().add(product);
		customerDao.update(customer);

	}

	public Customers getAllOrders(long id) {
		Customers customer = customerDao.findById(id);
		return customer;
	}

	public void createCustomer(String identify, String fname, String lname, String pass, String mail, String valueOf) {
		String email = mail+valueOf;
		Customers customers = new Customers(fname, lname, email, pass, identify);
		customerDao.save(customers);
	}

	public Customers updateProducts(long id, Product key) {

		Customers customer = customerDao.findById(id);
		customer.getProduct().add(key);
		customerDao.update(customer);
		return customer;
	}

}
