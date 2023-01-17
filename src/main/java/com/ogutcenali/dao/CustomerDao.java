package com.ogutcenali.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ogutcenali.entity.Admin;
import com.ogutcenali.entity.Customers;

import com.ogutcenali.utils.HibernateUtils;

public class CustomerDao implements ICrud<Customers> {

	
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
	public void save(Customers t) {
		try {
			openTransaction();
			session.save(t);
			accessTransaction();
		} catch (Exception e) {
			errorTransaction();
		}
		
	}

	@Override
	public void update(Customers t) {
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			
			transaction = session.beginTransaction();
			
			session.merge(t);
			transaction.commit();
		}catch (Exception e) {
			System.out.println("Update ogrencide patladık");
			if (transaction == null) {
				transaction.rollback();
			}
		}
		
	}

	@Override
	public void delete(long id) {
		try {
			Customers customer = findById(id);
			if (customer != null) {
				openTransaction();
				session.delete(customer);
				accessTransaction();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorTransaction();
		}
		
	}

	@Override
	public Customers findById(long id) {
		session = dataBaseConnectionHibernate();

		Customers customers;

		try {
			customers = session.find(Customers.class, id);
			if (customers != null) {
				return customers;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<Customers> listAll() {
		Session session = dataBaseConnectionHibernate();
		TypedQuery<Customers> userQuery = session.createQuery("from Customers", Customers.class);
		List<Customers> customers = userQuery.getResultList();
		return customers;
	}

}
