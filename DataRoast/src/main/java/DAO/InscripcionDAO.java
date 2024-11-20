package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import modelo.*;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InscripcionDAO implements DAOInterface<Inscripcion, Integer> {


    @Override
    public Inscripcion find(Integer numeroInscripcion) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(Inscripcion.class, numeroInscripcion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando inscripcion");
        }
    }

    @Override
    public Inscripcion insert(Inscripcion inscripcion) {
        validate(inscripcion);
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(inscripcion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error agregando inscripcion");
        }
        return inscripcion;
    }

    @Override
    public boolean delete(Integer numeroInscripcion) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                Inscripcion inscripcion = session.find(Inscripcion.class, numeroInscripcion);
                if (inscripcion != null)
                    session.remove(inscripcion);
                return (inscripcion != null);
            });
        } catch (Exception e){
            throw new DataErrorException("No se pudo eliminar la inscripcion");
        }
    }

    @Override
    public List<Inscripcion> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Inscripcion> query = builder.createQuery(Inscripcion.class);
                Root<Inscripcion> inscripcion = query.from(Inscripcion.class);
                query.select(inscripcion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    @Override
    public Inscripcion update(Inscripcion inscripcion) {
        throw new DataErrorException("No es posible actualizar inscripciones");
    }

    public List<Inscripcion> findBySocio(int numeroSocio) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Inscripcion> query = builder.createQuery(Inscripcion.class);
                Root<Inscripcion> inscripcion = query.from(Inscripcion.class);
                query.where(builder.equal(inscripcion.get(Inscripcion_.socio).get(Socio_.numeroSocio), numeroSocio));
                query.select(inscripcion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    public List<Inscripcion> findByDate(LocalDate fechaInferior, LocalDate fechaSuperior) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Inscripcion> query = builder.createQuery(Inscripcion.class);
                Root<Inscripcion> inscripcion = query.from(Inscripcion.class);
                query.where(builder.between(inscripcion.get(Inscripcion_.excursion).get(Excursion_.fecha), fechaInferior, fechaSuperior));
                query.select(inscripcion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    public List<Inscripcion> findByDateAndSocio(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<Inscripcion> query = builder.createQuery(Inscripcion.class);
                Root<Inscripcion> inscripcion = query.from(Inscripcion.class);
                query.where(builder.equal(inscripcion.get(Inscripcion_.socio).get(Socio_.numeroSocio), numeroSocio));
                query.where(builder.between(inscripcion.get(Inscripcion_.excursion).get(Excursion_.fecha), fechaInferior, fechaSuperior));
                query.select(inscripcion);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    private void validate(Inscripcion inscripcion){
        StringBuilder error = new StringBuilder();

        Validator validator = HibernateUtil.getValidator();
        Set<ConstraintViolation<Inscripcion>> constraintViolations = validator.validate(inscripcion);
        if (!constraintViolations.isEmpty()){
            for (ConstraintViolation<Inscripcion> violation : constraintViolations) {
                error.append("\n").append(violation.getMessage());
            }
            throw new DataErrorException(error.toString());
        }
    }
}