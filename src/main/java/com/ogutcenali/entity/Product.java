package com.ogutcenali.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private int stock;
	private byte[] image;

	private int price;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProductEvaluate> productevaluate;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Category category;

	@ManyToMany(mappedBy = "product",fetch = FetchType.LAZY)
	private List<Customers> customerList;
	
	

	public Product(String name, int stock, Category category) {
		super();
		this.name = name;
		this.stock = stock;
		this.category = category;
	}

	public Product(String name, int stock, byte[] image, Category category) {
		super();
		this.name = name;
		this.stock = stock;
		this.image = image;
		this.category = category;
	}

	public Product(String name, int stock, byte[] image, int price, Category category) {
		super();
		this.name = name;
		this.stock = stock;
		this.image = image;
		this.price = price;
		this.category = category;
	}

	public Product(int stock) {
		super();
		this.stock = stock;
	}

	public Product() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<ProductEvaluate> getProductevaluate() {
		return productevaluate;
	}

	public void setProductevaluate(Set<ProductEvaluate> productevaluate) {
		this.productevaluate = productevaluate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Customers> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customers> customerList) {
		this.customerList = customerList;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(category, id, name, price);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && id == other.id && Arrays.equals(image, other.image)
				&& Objects.equals(name, other.name) && price == other.price;
	}

	
	
	
	
	
	
	

	
	
	
	
	
	

}
