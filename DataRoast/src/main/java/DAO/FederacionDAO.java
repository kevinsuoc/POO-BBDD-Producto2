package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import modelo.Excursion;
import modelo.Federacion;
import modelo.Federacion_;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FederacionDAO implements DAOInterface<Federacion, String> {
    public FederacionDAO() {
    }

    @Override
    public Federacion find(String codigo) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(Federacion.class, codigo);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando federacion");
        }
    }

    @Override
    public List<Federacion> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Federacion> query = builder.createQuery(Federacion.class);
                Root<Federacion> federacion = query.from(Federacion.class);
                query.select(federacion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando federaciones");
        }
    }

    @Override
    public Federacion update(Federacion federacion) {
        validate(federacion);
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(federacion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando federaciocn");
        }
    }

    @Override
    public boolean delete(String codigo){
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                Federacion federacion = session.find(Federacion.class, codigo);
                if (federacion != null)
                    session.remove(federacion);
                return (federacion != null);
            });
        } catch (Exception e){
            throw new DataErrorException("No se pudo eliminar la federacion");
        }
    }

    @Override
    public Federacion insert(Federacion federacion) {
        validate(federacion);
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(federacion);
            });
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            throw new DataErrorException("Error agregando federacion");
        }
        return federacion;
    }

    private void validate(Federacion federacion){
        StringBuilder error = new StringBuilder();

        Validator validator = HibernateUtil.getValidator();
        Set<ConstraintViolation<Federacion>> constraintViolations = validator.validate(federacion);
        if (!constraintViolations.isEmpty()){
            for (ConstraintViolation<Federacion> violation : constraintViolations) {
                error.append("\n").append(violation.getMessage());
            }
            throw new DataErrorException(error.toString());
        }
    }
}
