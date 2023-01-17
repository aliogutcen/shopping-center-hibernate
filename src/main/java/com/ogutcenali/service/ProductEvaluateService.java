package com.ogutcenali.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.ogutcenali.dao.ProductEvaluateDao;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;
import com.ogutcenali.entity.ProductEvaluate;

public class ProductEvaluateService {

	ProductEvaluateDao productEvaDao = new ProductEvaluateDao();

	public List<ProductEvaluate> getAllComments() {

		return productEvaDao.listAll();
	}

	public void createEvalute(Product product2, Customers customers, String text) {
		ProductEvaluate productEvaluate = new ProductEvaluate();
		productEvaluate.setCustomer(customers);
		productEvaluate.setProduct(product2);
		productEvaDao.save(productEvaluate);

	}

	public List<ProductEvaluate> getAllCommentsWithCustomerId(Customers customer) {

		return productEvaDao.listAll().stream().filter((p) -> p.getCustomer().getId() == customer.getId()).toList();
	}

	public List<ProductEvaluate> getAllCommentsWithCustomerId(long id) {

		return productEvaDao.listAll().stream().filter((p) -> p.getProduct().getCategory().getId() == id).toList();
	}

	public List<ProductEvaluate> getListWithProductId(int parseInt) {

		return productEvaDao.listAll().stream().filter((p) -> p.getProduct().getId() == parseInt).toList();
	}

	public List<ProductEvaluate> getAllWithPuan(int value) {

		return productEvaDao.listAll().stream().filter((p) -> p.getPuan() == value).toList();
	}

	public long CommentsPuanWithCustomerId(long value) {

		long productHas = productEvaDao.listAll().stream().filter((p) -> p.getCustomer().getId() == value).count();

		long puan = productEvaDao.listAll().stream().filter((p) -> p.getCustomer().getId() == value)
				.mapToInt(p -> p.getPuan()).sum();

		long avgPuan = puan / productHas;

		return avgPuan;

	}

	public List<ProductEvaluate> getAll(long id) {

		return productEvaDao.listAll().stream().filter((p) -> p.getCustomer().getId() == id).toList();
	}

	public List<ProductEvaluate> getAllComments(long id) {

		return productEvaDao.listAll().stream().filter((p) -> p.getCustomer().getId() == id).toList();
	}

	public ProductEvaluate productEvaluateComments(int productId, long id) {
		ProductEvaluate productEva = productEvaDao.listAll().stream()
				.filter((p) -> p.getProduct().getId() == productId && p.getCustomer().getId() == id).findAny()
				.orElse(null);
		return productEva;
	}

	public void updateCommentsAndPuan(int parseInt, long id, String comments, int puan) {
		ProductEvaluate productEvaluate = productEvaDao.listAll().stream()
				.filter((p) -> p.getProduct().getId() == parseInt && p.getCustomer().getId() == id).findAny()
				.orElse(null);
		productEvaluate.setComment(comments);
		productEvaluate.setPuan(puan);
		productEvaDao.update(productEvaluate);

	}

	public HashMap<ProductEvaluate, Long> highestStarRating() {

		List<ProductEvaluate> productList = productEvaDao.listAll();
		HashMap<ProductEvaluate, Long> starForProduct = new HashMap<>();
		int count = 1;

		for (int i = 0; i < productList.size(); i++) {

			if (starForProduct.containsKey(productList.get(i))) {
				count++;
				long avg = (starForProduct.get(i) + productList.get(i).getPuan()) / count;
				starForProduct.replace(productList.get(i), starForProduct.get(avg));

			} else {
				starForProduct.put(productList.get(i), (long) productList.get(i).getPuan());
			}

		}

		return starForProduct;
	}

	public List<ProductEvaluate> getAllCommentsThisProduct(int value) {

		return productEvaDao.listAll().stream().filter(p -> p.getProduct().getId() == value).toList();
	}

	public ProductEvaluate getCommentsAboutProject(int value, long id) {
		ProductEvaluate productEvaluate = productEvaDao.listAll().stream()
				.filter(p -> p.getProduct().getId() == value && p.getCustomer().getId() == id).findAny().orElse(null);

		if (productEvaluate != null) {
			return productEvaluate;
		} else {
			return null;
		}

	}

	public void updateCommentsAndStar(  ProductEvaluate productEvaluate, String text, int parseInt) {
	
			productEvaluate.setComment(text);
			productEvaluate.setPuan(parseInt);
			productEvaDao.update(productEvaluate);
		

	}

	public void createCommentsAndStar(Customers customer, Product product, String text, int parseInt) {
		
		ProductEvaluate proEvaluate = new ProductEvaluate();
		proEvaluate.setCustomer(customer);
		proEvaluate.setProduct(product);
		proEvaluate.setComment(text);
		proEvaluate.setPuan(parseInt);
		productEvaDao.save(proEvaluate);
	}

}
