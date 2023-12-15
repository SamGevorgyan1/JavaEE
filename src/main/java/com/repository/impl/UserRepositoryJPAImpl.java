package com.repository.impl;

import com.model.User;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryJPAImpl {
   static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public static List<User> getAll(){

        Session session = sessionFactory.openSession();

        Query<User> nativeQuery = session.createNativeQuery("select * from user", User.class);
        return nativeQuery.getResultList();

    }

    public  static void save (User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();



    }
}
