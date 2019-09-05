package com.handtruth.bot.fun.dao;

import com.handtruth.bot.fun.entities.Admin;
import com.handtruth.bot.fun.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Admin.class, id);
    }

    @Override
    public void save(Admin admin) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(admin);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Admin admin) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(admin);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Admin admin) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(admin);
        tx1.commit();
        session.close();
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("DELETE FROM Admin");
        Transaction tx1 = session.beginTransaction();
        int result = query.executeUpdate();
        session.flush();
        tx1.commit();
        session.close();
    }


    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    @Override
    public List<Admin> findAll() {
        List admin = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("FROM Admin").list();
        return (List<Admin>) admin;
    }

    @Override
    public boolean isAdmin(long id) {
        Admin admin = findById(id);
        return admin != null;
    }
}
