package com.hibernate.startup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FirstLevelCacheApp {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Student s1 = (Student) session.get(Student.class, 3);
		//s1.setAge(110);
		// This will not fire the select query, as already session object has
		// the selected information.
		// See the difference in the console, only one select statement will be
		// executed
		Student s2 = (Student) session.get(Student.class, 3);

		System.out.println(s1.toString());
		// same output from s1, without second select query
		System.out.println(s2.toString());

		session.getTransaction().commit();
		session.close();
	}
}
