package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import modelo.SocioInfantil;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;

public class SocioInfantilDAO extends SocioDAO implements DAOInterface<SocioInfantil, Integer> {

    @Override
    public SocioInfantil insert(SocioInfantil socio) {
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
    public SocioInfantil find(Integer id) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.find(SocioInfantil.class, id);
            });
        } catch (Exception e){
            throw new DataErrorException("Error buscando socio");
        }

    }

    @Override
    public List<SocioInfantil> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            return sessionFactory.fromTransaction(session -> {
                var builder = sessionFactory.getCriteriaBuilder();
                CriteriaQuery<SocioInfantil> query = builder.createQuery(SocioInfantil.class);
                Root<SocioInfantil> socioInfantil = query.from(SocioInfantil.class);
                query.select(socioInfantil);
                return session.createSelectionQuery(query).getResultList();
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new DataErrorException("Error buscando socios");
        }
    }

    @Override
    public SocioInfantil update(SocioInfantil socio) {
        validate(socio);
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(socio);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando socio");
        }
    }

    private void validate(SocioInfantil socio){
        StringBuilder error = new StringBuilder();

        Validator validator = HibernateUtil.getValidator();
        Set<ConstraintViolation<SocioInfantil>> constraintViolations = validator.validate(socio);
        if (!constraintViolations.isEmpty()){
            for (ConstraintViolation<SocioInfantil> violation : constraintViolations) {
                error.append("\n").append(violation.getMessage());
            }
            throw new DataErrorException(error.toString());
        }
    }
}
