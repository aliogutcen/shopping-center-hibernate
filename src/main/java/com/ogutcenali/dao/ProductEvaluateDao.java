package com.ogutcenali.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.ProductEvaluate;
import com.ogutcenali.utils.HibernateUtils;

public class ProductEvaluateDao implements ICrud<ProductEvaluate> {
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
	public void save(ProductEvaluate t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}

	}

	@Override
	public void update(ProductEvaluate t) {
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
			ProductEvaluate ProductEvaluate = findById(id);
			if (ProductEvaluate != null) {
				openTransaction();
				session.delete(ProductEvaluate);
				accessTransaction();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorTransaction();
		}

	}

	@Override
	public ProductEvaluate findById(long id) {
		session = dataBaseConnectionHibernate();

		ProductEvaluate ProductEvaluate;

		try {
			ProductEvaluate = session.find(ProductEvaluate.class, id);
			if (ProductEvaluate != null) {
				return ProductEvaluate;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<ProductEvaluate> listAll() {
		Session session = dataBaseConnectionHibernate();
		TypedQuery<ProductEvaluate> userQuery = session.createQuery("from ProductEvaluate", ProductEvaluate.class);
		List<ProductEvaluate> student = userQuery.getResultList();
		return student;
	}
	
	
	
	
	
	
	
}
