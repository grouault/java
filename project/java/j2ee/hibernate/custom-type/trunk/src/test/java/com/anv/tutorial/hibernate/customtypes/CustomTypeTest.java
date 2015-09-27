package com.anv.tutorial.hibernate.customtypes;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class CustomTypeTest {

	private Session session;

	@Before
	public void setUp() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}
	
	@Test
	public void shouldSaveAndLoadMyClass() throws Exception {
		Something something = new Something();
		something.setWeight(new Kilos(2));

		session.save(something);
		session.getTransaction().commit();

		Session anotherSession = HibernateUtil.getSessionFactory().openSession();
		
		Something somethingElse = (Something) anotherSession.createQuery("from Something")
				.uniqueResult();
		HibernateUtil.getSessionFactory().close();

		assertEquals(something, somethingElse);
		assertEquals(2, somethingElse.getWeight().getNumber());
	}
}
