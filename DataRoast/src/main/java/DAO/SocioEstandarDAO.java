package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import modelo.SocioEstandar;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;

public class SocioEstandarDAO extends SocioDAO implements DAOInterface<SocioEstandar, Integer>  {

    @Override
    public SocioEstandar insert(SocioEstandar socio) {
        validate(socio);
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(socio);
            });
        } catch (Exception e){
            throw new DataErrorException("Error agregando socio");
        }
        return socio;
    }

    @Override
    public SocioEstandar find(Integer id) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(SocioEstandar.class, id);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando socio");
        }

    }

    @Override
    public List<SocioEstandar> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<SocioEstandar> query = builder.createQuery(SocioEstandar.class);
                Root<SocioEstandar> socioEstandar = query.from(SocioEstandar.class);
                query.select(socioEstandar);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando socios");
        }
    }

    @Override
    public SocioEstandar update(SocioEstandar socio) {
        validate(socio);
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(socio);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando socio");
        }
    }

    private void validate(SocioEstandar socio){
        StringBuilder error = new StringBuilder();

        Validator validator = HibernateUtil.getValidator();
        Set<ConstraintViolation<SocioEstandar>> constraintViolations = validator.validate(socio);
        if (!constraintViolations.isEmpty()){
            for (ConstraintViolation<SocioEstandar> violation : constraintViolations) {
                error.append("\n").append(violation.getMessage());
            }
            throw new DataErrorException(error.toString());
        }
    }
}