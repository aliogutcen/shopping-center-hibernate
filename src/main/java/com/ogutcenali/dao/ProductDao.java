package com.ogutcenali.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.Product;

import com.ogutcenali.utils.HibernateUtils;

public class ProductDao implements ICrud<Product> {

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
	public void save(Product t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}

	}

	@Override
	public void update(Product t) {
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
			Product Product = findById(id);
			if (Product != null) {
				openTransaction();
				session.delete(Product);
				accessTransaction();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorTransaction();
		}

	}

	@Override
	public Product findById(long id) {
		session = dataBaseConnectionHibernate();

		Product Product;

		try {
			Product = session.find(Product.class, id);
			if (Product != null) {
				return Product;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<Product> listAll() {
		Session session = dataBaseConnectionHibernate();
		TypedQuery<Product> userQuery = session.createQuery("from Product", Product.class);
		List<Product> student = userQuery.getResultList();
		return student;
	}
	
	
	
	

}
