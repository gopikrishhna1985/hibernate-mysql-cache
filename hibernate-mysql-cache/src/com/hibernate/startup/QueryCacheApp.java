package com.hibernate.startup;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryCacheApp {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Student where id = ?");
		query.setInteger(0, 3);
		//Important, tells to cache the query results
		query.setCacheable(true);
		List<Student> student = query.list();
		System.out.println(student.get(0).toString());
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		
		Query query2 = session2.createQuery("from Student where id = ?");
		//Important, will tell to look into query cache
		query2.setCacheable(true);
		query2.setInteger(0, 3);

		List<Student> student1 = query2.list();
		System.out.println(student1.get(0).toString());
		session2.getTransaction().commit();
		session2.close();
	}
}
