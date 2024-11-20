package util;

import modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static void startSessionFactory(){
        sessionFactory = new Configuration().
                addAnnotatedClass(Federacion.class).
                addAnnotatedClass(Excursion.class).
                addAnnotatedClass(SocioEstandar.class).
                addAnnotatedClass(Seguro.class).
                addAnnotatedClass(SocioFederado.class).
                addAnnotatedClass(SocioInfantil.class).
                addAnnotatedClass(Inscripcion.class).
                setProperty("hibernate.hbm2ddl.auto", "validate").
                setProperty(URL, "jdbc:mysql://localhost:3306/senderosymontanas").
                setProperty(USER, "dataroast").
                setProperty(PASS, "dataroastpassword").
                setProperty(SHOW_SQL, true).
                setProperty(FORMAT_SQL, true).
                setProperty(HIGHLIGHT_SQL, true).
                buildSessionFactory();
    }

    public static void endSessionFactory(){
        sessionFactory.close();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
