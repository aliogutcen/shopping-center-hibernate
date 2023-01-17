package com.ogutcenali.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.Category;

import com.ogutcenali.utils.HibernateUtils;

public class CategoryDao implements ICrud<Category> {

	private Session session;
	private Transaction transaction;

	private void openTransaction() {

		session = HibernateUtils.getSessionFactory().openSession();
		transaction = session.beginTransaction();

	}

	private void accessTransaction() {
		transaction.commit();
		session.close();
	}

	private void errorTransaction() {
		if (transaction == null) {
			transaction.rollback();
		}

	}

	@Override
	public void save(Category t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}

	}

	@Override
	public void update(Category t) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();

			session.merge(t);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Update ogrencide patladık");
			if (transaction == null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void delete(long id) {
		try {
			Category Category = findById(id);
			if (Category != null) {
				openTransaction();
				session.delete(Category);
				accessTransaction();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorTransaction();
		}

	}

	@Override
	public Category findById(long id) {
		session = dataBaseConnectionHibernate();

		Category Category;

		try {
			Category = session.find(Category.class, id);
			if (Category != null) {
				return Category;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<Category> listAll() {
		Session session = dataBaseConnectionHibernate();
		TypedQuery<Category> userQuery = session.createQuery("from Category", Category.class);
		List<Category> student = userQuery.getResultList();
		return student;
	}
	
	
	
	

}
