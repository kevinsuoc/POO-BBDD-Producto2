package DAO;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import modelo.Excursion;
import org.hibernate.SessionFactory;
import util.DataErrorException;
import util.HibernateUtil;
import util.MysqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        try {
            HibernateUtil.getSessionFactory().inTransaction(session -> {
                session.persist(excursion);
            });
        } catch (Exception e){
            throw new DataErrorException("Error agregando excursion");
        }
        return excursion;
    }

    //todo: Find by
    public List<Excursion> findByDateRange(LocalDate fechaInferior, LocalDate fechaSuperior){
        String findQuery = "SELECT * FROM excursion WHERE fecha BETWEEN ? AND ?;";

        ArrayList<Excursion> excursiones = new ArrayList<>();
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setDate(1, Date.valueOf(fechaInferior));
            stm.setDate(2, Date.valueOf(fechaSuperior));
            try (ResultSet results = stm.executeQuery()) {
                while (results.next()) {
                    excursiones.add(new Excursion(
                            results.getInt("num_dias"),
                            results.getDouble("precio_inscripcion"),
                            results.getString("codigo"),
                            results.getString("descripcion"),
                            results.getDate("fecha").toLocalDate()
                    ));
                }
                return excursiones;
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando excursiones");
        }
    }
}