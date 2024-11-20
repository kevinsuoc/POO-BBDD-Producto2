package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.*;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioEstandarDAO extends SocioDAO implements DAOInterface<SocioEstandar, Integer>  {

    @Override
    public SocioEstandar insert(SocioEstandar socio) {
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
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                return session.merge(socio);
            });
        } catch (Exception e){
            throw new DataErrorException("Error actualizando socio");
        }
    }
}