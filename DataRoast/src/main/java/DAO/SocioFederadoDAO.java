package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.SocioFederado;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;

import java.util.List;

public class SocioFederadoDAO extends SocioDAO implements DAOInterface<SocioFederado, Integer> {
    @Override
    public SocioFederado insert(SocioFederado socio) {
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
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(socio);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando socio");
        }
    }
}
