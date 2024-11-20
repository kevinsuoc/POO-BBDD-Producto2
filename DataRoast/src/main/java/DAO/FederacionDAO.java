package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.Federacion;
import modelo.Federacion_;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            e.printStackTrace();
            System.out.println(e);
            throw new DataErrorException("Error buscando federaciones");
        }
    }

    @Override
    public Federacion update(Federacion federacion) {
        String updateQuery = "UPDATE federacion SET nombre = ? WHERE codigo = ?";

        try (Connection con = MysqlConnection.getConnection()){
            PreparedStatement stm = con.prepareStatement(updateQuery);

            stm.setString(1, federacion.getNombre());
            stm.setString(2, federacion.getCodigo());

            if (stm.executeUpdate() == 1)
                return federacion;
            return null;
        } catch (SQLException e) {
            throw new DataErrorException("Error actualizando federacion");
        }
    }

    @Override
    public boolean delete(String codigo){
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                Federacion federacion = session.find(Federacion.class, codigo);
                System.out.println(federacion);
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
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(federacion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error agregando federacion");
        }
        return federacion;
    }
}
