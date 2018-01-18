package org.justd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.Config;


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
