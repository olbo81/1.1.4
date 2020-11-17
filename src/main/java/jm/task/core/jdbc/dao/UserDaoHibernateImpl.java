package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction createTable = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            createTable = session.beginTransaction();
            String query = "CREATE TABLE IF NOT EXISTS user" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(50) NOT NULL, " +
                    " lastName VARCHAR (50) NOT NULL, " +
                    " age TINYINT NOT NULL, " +
                    " PRIMARY KEY (id))";
            session.createSQLQuery(query).executeUpdate();
            createTable.commit();
        } catch (Exception e) {
            if (createTable != null) {
                createTable.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction dropTable = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            dropTable = session.beginTransaction();
            //String query = "DROP TABLE IF EXISTS users";
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            dropTable.commit();
        } catch (Exception e) {
            if (dropTable != null) {
                dropTable.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction saveUser = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            saveUser = session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.printf("User с именем – %s добавлен в базу данных %n", name);
        } catch (Exception e) {
            if (saveUser != null) {
                saveUser.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction removeUser = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            removeUser = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :Id");
            query.setParameter("Id", id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (removeUser != null) {
                removeUser.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction getAll = null;
        List<User> usersList = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            getAll = session.beginTransaction();
            Query query = session.createQuery("FROM User");
            usersList = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (getAll != null) {
                getAll.rollback();
            }
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        Transaction cleanUsersTab = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            cleanUsersTab = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (cleanUsersTab != null) {
                cleanUsersTab.rollback();
            }
            e.printStackTrace();
        }
    }
}



