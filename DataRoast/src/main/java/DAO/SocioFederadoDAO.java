package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import modelo.SocioFederado;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;

public class SocioFederadoDAO extends SocioDAO implements DAOInterface<SocioFederado, Integer> {
    @Override
    public SocioFederado insert(SocioFederado socio) {
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
    public SocioFederado find(Integer id) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(SocioFederado.class, id);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando socio");
        }

    }

    @Override
    public List<SocioFederado> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<SocioFederado> query = builder.createQuery(SocioFederado.class);
                Root<SocioFederado> socioFederado = query.from(SocioFederado.class);
                query.select(socioFederado);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            throw new DataErrorException("Error buscando socios");
        }
    }

    @Override
    public SocioFederado update(SocioFederado socio) {
        validate(socio);
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(socio);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando socio");
        }
    }

    private void validate(SocioFederado socio){
        StringBuilder error = new StringBuilder();

        Validator validator = HibernateUtil.getValidator();
        Set<ConstraintViolation<SocioFederado>> constraintViolations = validator.validate(socio);
        if (!constraintViolations.isEmpty()){
            for (ConstraintViolation<SocioFederado> violation : constraintViolations) {
                error.append("\n").append(violation.getMessage());
            }
            throw new DataErrorException(error.toString());
        }
    }
}
