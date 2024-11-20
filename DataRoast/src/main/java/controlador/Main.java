package controlador;

import modelo.Federacion;
import modelo.Federacion_;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.Scanner;

import static org.hibernate.cfg.AvailableSettings.*;

public class Main {
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//
//        Controlador controlador = new Controlador(in);
//        controlador.ejecutarPrograma();
//
//        in.close();
//    }
    public static void main (String[] args){

        Federacion fed = new Federacion("Tsts", "Federacion");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        sessionFactory.inTransaction(session -> {
            session.persist(fed);
        });

        sessionFactory.inSession(session -> {
            System.out.println(session.createSelectionQuery("select codigo||': '||nombre from Federacion where codigo = 'Tsts'", String.class).getSingleResult());
        });

        // query data using criteria API
        sessionFactory.inSession(session -> {
            var builder = sessionFactory.getCriteriaBuilder();
            var query = builder.createQuery(String.class);
            var federacion = query.from(Federacion.class);
            query.select(builder.concat(builder.concat(federacion.get(Federacion_.codigo), builder.literal(": ")),
                    federacion.get(Federacion_.codigo)));
            System.out.println(session.createSelectionQuery(query).getResultList());
        });

        sessionFactory.inTransaction(session -> {
            session.remove(fed);
        });
    }
}
