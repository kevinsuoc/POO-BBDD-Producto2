package dataroast.DAO;

import dataroast.modelo.Excursion;
import dataroast.util.MysqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDAO implements DAOInterface<Excursion, String> {

    @Override
    public Excursion find(String codigo) {
        String findQuery = "SELECT * FROM excursion WHERE codigo = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setString(1, codigo);
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()) {
                    return new Excursion(
                        results.getInt("num_dias"),
                        results.getDouble("precio_inscripcion"),
                        results.getString("codigo"),
                        results.getString("descripcion"),
                        results.getDate("fecha").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error buscando excursion: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Excursion> findAll() {
        String findQuery = "SELECT * FROM excursion";
        List<Excursion> excursiones = new ArrayList<>();

        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
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
            }
            return excursiones;
        } catch (SQLException e) {
            throw new DAOException("Error buscando excursiones: " + e.getMessage());
        }
    }

    @Override
    public Excursion update(Excursion excursion) {
        String updateQuery = "UPDATE excursion SET num_dias = ?, precio_inscripcion = ?, descripcion = ?, fecha = ? WHERE codigo = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(updateQuery);

            stm.setInt(1, excursion.getNumDias());
            stm.setDouble(2, excursion.getPrecioInscripcion());
            stm.setString(3, excursion.getDescripcion());
            stm.setDate(4, Date.valueOf(excursion.getFecha()));
            stm.setString(5, excursion.getCodigo());

            if (stm.executeUpdate() == 1) {
                return excursion;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error actualizando excursion: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(String codigo) {
        String deleteQuery = "DELETE FROM excursion WHERE codigo = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(deleteQuery);
            stm.setString(1, codigo);
            return stm.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DAOException("Error borrando excursion: " + e.getMessage());
        }
    }

    @Override
    public Excursion insert(Excursion excursion) {
        String insertQuery = "INSERT INTO excursion (num_dias, precio_inscripcion, codigo, descripcion, fecha) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(insertQuery);
            stm.setInt(1, excursion.getNumDias());
            stm.setDouble(2, excursion.getPrecioInscripcion());
            stm.setString(3, excursion.getCodigo());
            stm.setString(4, excursion.getDescripcion());
            stm.setDate(5, Date.valueOf(excursion.getFecha()));

            if (stm.executeUpdate() == 1) {
                return excursion;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error insertando excursion: " + e.getMessage());
        }
    }

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
            throw new DAOException("Error buscando excursiones por fecha: " + e.getMessage());
        }
    }
}