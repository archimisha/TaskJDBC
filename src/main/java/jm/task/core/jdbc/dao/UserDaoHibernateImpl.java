package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Transaction transaction;
    private SessionFactory sessionFactory;
    private Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE Users ("
                + "id INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(45),"
                + "lastName VARCHAR(45),"
                + "age INT(64))");
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void dropUsersTable() {
        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public void removeUserById(long id) {
        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User deleteUser = (User) session.get(User.class, id);
        session.delete(deleteUser);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Override
    public List<User> getAllUsers() {
        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory = Util.getSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
