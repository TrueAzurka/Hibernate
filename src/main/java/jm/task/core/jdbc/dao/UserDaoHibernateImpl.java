package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private static final String ADD_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS users  (
            id SERIAL PRIMARY KEY ,
            name VARCHAR(255) NOT NULL ,
            lastname VARCHAR(255) NOT NULL ,
            age INT NOT NULL )
            """;
    private static final String DROP_TABLE_SQL = """
            DROP TABLE IF EXISTS users
            """;


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tr = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id bigserial PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255), age smallint)").executeUpdate();
            tr.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tr = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            tr.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tr = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tr.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tr = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            tr.commit();
            System.out.println("User с ID " + id + " удален из базы данных");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("From users", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tr = session.beginTransaction();
            session.createQuery("delete from users").executeUpdate();
            tr.commit();
        }
    }
}
