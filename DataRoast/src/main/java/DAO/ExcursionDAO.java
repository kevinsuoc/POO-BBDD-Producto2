package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import modelo.Excursion;
import modelo.Excursion_;
import modelo.SocioFederado;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExcursionDAO implements DAOInterface<Excursion, String> {

    @Override
    public Excursion find(String codigo) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(Excursion.class, codigo);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando excursion");
        }
    }

    @Override
    public List<Excursion> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Excursion> query = builder.createQuery(Excursion.class);
                Root<Excursion> excursion = query.from(Excursion.class);
                query.select(excursion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando excursiones");
        }
    }

    @Override
    public Excursion update(Excursion excursion) {
        validate(excursion);
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(excursion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando excursion");
        }
    }

    @Override
    public boolean delete(String codigo) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                Excursion excursion = session.find(Excursion.class, codigo);
                if (excursion != null)
                    session.remove(excursion);
                return (excursion != null);
            });
        } catch (Exception e){
            throw new DataErrorException("No se pudo eliminar la excursion");
        }
    }

    @Override
    public Excursion insert(Excursion excursion) {
        validate(excursion);
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(excursion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error agregando excursion");
        }
        return excursion;
    }

    public List<Excursion> findByDateRange(LocalDate fechaInferior, LocalDate fechaSuperior){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = session.getCriteriaBuilder();
                CriteriaQuery<Excursion> query = builder.createQuery(Excursion.class);
                Root<Excursion> excursion = query.from(Excursion.class);
                query.where(builder.between(excursion.get(Excursion_.fecha), fechaInferior, fechaSuperior));
                query.select(excursion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando excuriones");
        }
    }

    private void validate(Excursion excursion){
        StringBuilder error = new StringBuilder();

        Validator validator = HibernateUtil.getValidator();
        Set<ConstraintViolation<Excursion>> constraintViolations = validator.validate(excursion);
        if (!constraintViolations.isEmpty()){
            for (ConstraintViolation<Excursion> violation : constraintViolations) {
                error.append("\n").append(violation.getMessage());
            }
            throw new DataErrorException(error.toString());
        }
    }
}