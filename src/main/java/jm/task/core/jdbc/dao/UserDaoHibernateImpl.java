package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

import java.util.List;

import static jm.task.core.jdbc.util.Util.connectHibernate;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = connectHibernate()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(" +
                    "userid SERIAL PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "age INT);").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.getMessage();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = connectHibernate()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.getMessage();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User tempUser = new User(name, lastName, age);

        try (Session session = connectHibernate()) {
            session.beginTransaction();
            session.save(tempUser);
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.getMessage();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = connectHibernate()) {
            session.beginTransaction();
            User tempUser = session.get(User.class, id);
            session.delete(tempUser);
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.getMessage();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = connectHibernate()) {
            session.beginTransaction();
            List<User> userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return userList;
        } catch (Throwable e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = connectHibernate()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.getMessage();
        }
    }
}
