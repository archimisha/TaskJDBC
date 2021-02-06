package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/hibernate";
    private final String USERNAME = "root";
    private final String PASSWORD = "12345";

    private static SessionFactory sessionFactory;

    private Connection con;

    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        Configuration configuration = new Configuration();
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.URL, "jdbc:mysql://127.0.0.1:3306/hibernate");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "12345");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
