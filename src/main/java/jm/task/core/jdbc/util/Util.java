package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static Connection connectJDBC() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "newpassword");
    }

    public static Session connectHibernate() throws Throwable{

        //Установка пропсов
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        properties.setProperty("dialect", "org.hibernate.dialect.PostgresSQL");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "newpassword");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

        //Создание Фабрики
        SessionFactory sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        return sessionFactory.openSession();
    }

}