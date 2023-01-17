package com.ogutcenali.service;

import java.util.List;

import com.ogutcenali.dao.CategoryDao;
import com.ogutcenali.entity.Category;

public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();

	public boolean saveNewCategory(String categoryName) {

		Category category = categoryDao.listAll()
				.stream()
				.filter(c -> c.getName().equals(categoryName))
				.findAny()
				.orElse(null);

		if (category == null) {
			category = new Category(categoryName);
			categoryDao.save(category);
			return true;
		}

		return false;
	}

	public List<Category> getAllList() {
		return categoryDao.listAll();
	}

	public void deleteCategory(long parseLong) {
		categoryDao.delete(parseLong);
	}

	public Category findById(long value) {
		return categoryDao.findById(value);
	}

	public Category findByServiceId(String valueOf) {

		Category category = categoryDao.listAll()
				.stream()
				.filter((c) -> c.getName().equals(valueOf))
				.findAny()
				.orElse(null);

		if (category != null) {
			return category;
		}

		return null;

	}

	public Category getIdWithCategoryname(String categoryname) {

		return categoryDao.listAll().stream().filter((c) -> c.getName().equals(categoryname)).findAny().orElse(null);
	}

	public void updateWithId(long parseLong, String text) {
		Category category = categoryDao.findById(parseLong);
		category.setName(text);
		categoryDao.update(category);

	}

}
