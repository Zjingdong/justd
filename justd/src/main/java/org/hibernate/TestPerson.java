package org.hibernate;

import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class TestPerson {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    @Test
    public void testPersion(){
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Person p = new Person();
        p.setId(1);
        p.setName("zjd");
        p.setUser("just");
        p.setPasswd("123");
        session.save(p);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
