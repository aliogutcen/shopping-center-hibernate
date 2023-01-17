package com.ogutcenali.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.Admin;

import com.ogutcenali.utils.HibernateUtils;

public class AdminDao implements ICrud<Admin> {

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
	public void save(Admin t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}

	}

	@Override
	public void update(Admin t) {
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
			Admin admin = findById(id);
			if (admin != null) {
				openTransaction();
				session.delete(admin);
				accessTransaction();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorTransaction();
		}

	}

	@Override
	public Admin findById(long id) {
		session = dataBaseConnectionHibernate();

		Admin admin;

		try {
			admin = session.find(Admin.class, id);
			if (admin != null) {
				return admin;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<Admin> listAll() {
		Session session = dataBaseConnectionHibernate();
		TypedQuery<Admin> userQuery = session.createQuery("from Admin", Admin.class);
		List<Admin> student = userQuery.getResultList();
		return student;
	}

}
