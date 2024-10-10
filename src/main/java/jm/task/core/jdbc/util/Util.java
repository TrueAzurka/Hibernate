package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory sessionFactory;
    // реализуйте настройку соеденения с БД

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
                config.setProperty("hibernate.connection.username", "postgres");
                config.setProperty("hibernate.connection.password", "s11663050s");
                config.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
