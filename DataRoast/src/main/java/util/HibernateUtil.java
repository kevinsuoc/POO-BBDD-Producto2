package util;

import modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static void startSessionFactory(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static void endSessionFactory(){
        sessionFactory.close();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
