package com.ogutcenali.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.ogutcenali.dao.CustomerDao;
import com.ogutcenali.dao.ProductDao;
import com.ogutcenali.entity.Category;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;

public class ProductService {

	private ProductDao productDao = new ProductDao();
	private CategoryService categoryService = new CategoryService();
	private CustomerDao customerDao = new CustomerDao();

	public List<Product> getAll() {

		return productDao.listAll();
	}

	public void createNewProduct(String text, int parseInt, byte[] data, Category category, int i) {

		Product product = new Product(text, parseInt, data, i, category);
		productDao.save(product);
	}

	public List<Product> getListWithCategoryId(long parseLong) {

		List<Product> productList = productDao.listAll().stream().filter((p) -> p.getCategory().getId() == parseLong)
				.toList();
		return productList;
	}

	public Product FindInfoWithId(long value) {
		Product product = productDao.findById(value);
		return product;
	}

	public void StockUpdateForId(long parseLong, int updateStock) {

		Product product = productDao.findById(parseLong);
		product.setStock(product.getStock() + updateStock);
		productDao.update(product);

	}

	public void productDeleteWithById(long parseLong) {

		Product product = productDao.findById(parseLong);
		product.getCustomerList().removeAll(customerDao.listAll());

		productDao.delete(parseLong);

	}

	public List<Product> getCategoryName(String categoryname) {

		Category category = categoryService.getIdWithCategoryname(categoryname);

		return productDao.listAll().stream().filter((p) -> p.getCategory().getId() == category.getId()).toList();
	}

	public List<Product> getAll(String valueOf) {

		return productDao.listAll().stream().filter((p) -> p.getCategory().getName().equals(valueOf)).toList();
	}

	public Product getAllInfoWithId(long value) {

		return productDao.listAll().stream().filter((p) -> p.getId() == value).findAny().orElse(null);
	}

	public Product getAllInfoWithId(int id, int stock) {
		Product product = productDao.findById(id);
		product.setStock(product.getStock() - stock);
		productDao.update(product);
		return product;
	}

	public List<Product> getAllMoreLessTen() {

		return productDao.listAll().stream().filter((p) -> p.getStock() < 10).toList();
	}



	public void updateThisProduct(int parseInt, String text, long parseLong) {
		Product product = productDao.findById(parseLong);
		product.setPrice(parseInt);
		product.setName(text);
		productDao.update(product);

	}

	public void StockUpdateForBasket(long id, Integer value) {
		Product product = productDao.findById(id);
		product.setStock(product.getStock() - value);
		productDao.update(product);
		
	}

}
