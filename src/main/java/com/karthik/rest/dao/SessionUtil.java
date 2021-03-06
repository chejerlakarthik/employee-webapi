package com.karthik.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {
	
	private static SessionUtil instance = new SessionUtil();
	private SessionFactory sessionFactory;
	
	private SessionUtil() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}
	
	private static SessionUtil getInstance() {
		return instance;
	}
	
	private static Session getSession() {
		Session session = getInstance().sessionFactory.getCurrentSession();
		return session;
	}
	
	public static Session beginTransaction() {
		Session session = getSession();
		session.beginTransaction();
		return session;
	}
	
	public static void commitTransaction(Session session) {
		session.getTransaction().commit();
		//session.close();
	}

}
