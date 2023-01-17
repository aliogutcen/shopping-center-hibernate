package com.ogutcenali.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Customers extends Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String password;

	private String identity;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductEvaluate> productEvaluate;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Product> product;

	public Customers(String firstName, String lastName, String email, String password, String identity) {
		super(firstName, lastName, email);

		this.password = password;
		this.identity = identity;
	}

	public Customers() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<ProductEvaluate> getProductEvaluate() {
		return productEvaluate;
	}

	public void setProductEvaluate(List<ProductEvaluate> productEvaluate) {
		this.productEvaluate = productEvaluate;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
