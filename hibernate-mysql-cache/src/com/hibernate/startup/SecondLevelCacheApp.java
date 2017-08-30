package com.hibernate.startup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SecondLevelCacheApp {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Student s1 = (Student) session.get(Student.class, 3);
		System.out.println(s1.toString());
		session.getTransaction().commit();
		session.close();

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		//Second select statement will not fire, will get from EhCache
		Student s2 = (Student) session2.get(Student.class, 3);
		System.out.println(s2.toString());
		session2.getTransaction().commit();
		session2.close();
		
		//In the Console you will see one select statement and two results, 
		//the second one was fetched from the Second Level Cache
		
		//Hibernate: select student0_.id as id1_0_0_, student0_.age as age2_0_0_, student0_.firstName as firstNam3_0_0_, student0_.lastName as lastName4_0_0_, student0_.school as school5_0_0_, student0_.section as section6_0_0_ from Student student0_ where student0_.id=?
		//Student [id=3, firstName=firstName3, lastName=lastName3, age=4, section=section3, school=school3]
		//Student [id=3, firstName=firstName3, lastName=lastName3, age=4, section=section3, school=school3]
		
	}
}
