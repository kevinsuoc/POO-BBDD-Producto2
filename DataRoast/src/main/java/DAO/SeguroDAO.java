package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.Excursion;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import modelo.Seguro;
import modelo.TipoSeguro;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO implements DAOInterface<Seguro, TipoSeguro>{
    @Override
    public Seguro insert(Seguro entity) {
        throw new DataErrorException("No es posible agregar nuevos seguros");
    }

    @Override
    public Seguro find(TipoSeguro tipoSeguro) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(Seguro.class, tipoSeguro);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando seguro");
        }
    }

    @Override
    public List<Seguro> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Seguro> query = builder.createQuery(Seguro.class);
                Root<Seguro> seguro = query.from(Seguro.class);
                query.select(seguro);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando seguros");
        }
    }

    @Override
    public Seguro update(Seguro entity) {
        throw new DataErrorException("No es posible actualizar seguros");
    }

    @Override
    public boolean delete(TipoSeguro tipoSeguro) {
        throw new DataErrorException("No es posible borrar seguros");
    }
}
