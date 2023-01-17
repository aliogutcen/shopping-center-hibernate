package com.ogutcenali.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ogutcenali.entity.Admin;
import com.ogutcenali.entity.Category;
import com.ogutcenali.entity.Customers;
import com.ogutcenali.entity.Product;
import com.ogutcenali.entity.ProductEvaluate;

public class HibernateUtils {

	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {

		try {
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(Admin.class);
			configuration.addAnnotatedClass(Customers.class);
			configuration.addAnnotatedClass(Category.class);
			configuration.addAnnotatedClass(Product.class);
			configuration.addAnnotatedClass(ProductEvaluate.class);
			

			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

			return factory;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

}
