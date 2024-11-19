package DAO;

import modelo.Inscripcion;
import util.DataErrorException;
import modelo.Socio;
import modelo.Excursion;
import util.MysqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO implements DAOInterface<Inscripcion, Integer> {

    @Override
    public Inscripcion find(Integer numeroInscripcion) {
        String findQuery = "SELECT numero_inscripcion," +
                "inscripcion.id_socio, " +
                "codigo_excursion, " +
                "nombre, " +
                "descripcion, " +
                "num_dias, " +
                "precio_inscripcion, " +
                "fecha " +
                "FROM inscripcion " +
                "JOIN excursion ON codigo = codigo_excursion " +
                "JOIN socio ON inscripcion.id_socio = socio.id_socio " +
                "WHERE numero_inscripcion = ?;";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setInt(1, numeroInscripcion);
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()){
                    return new Inscripcion(
                            results.getInt("numero_inscripcion"),
                            new Socio(
                                    results.getString("nombre"),
                                    results.getInt("id_socio")
                            ),
                            new Excursion(
                                    results.getInt("num_dias"),
                                    results.getDouble("precio_inscripcion"),
                                    results.getString("codigo_excursion"),
                                    results.getString("descripcion"),
                                    results.getDate("fecha").toLocalDate()
                            )
                    );
                }
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
        return null;
    }

    @Override
    public Inscripcion insert(Inscripcion inscripcion) {
        String insertQuery = "INSERT INTO inscripcion (numero_inscripcion, id_socio, codigo_excursion) VALUES (?, ?, ?)";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, inscripcion.getNumeroInscripcion());
            stm.setInt(2, inscripcion.getSocio().getNumeroSocio());
            stm.setString(3, inscripcion.getExcursion().getCodigo());
            stm.execute();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()){
                inscripcion.setNumeroInscripcion(generatedKeys.getInt(1));
            }
            return  inscripcion;
        } catch (SQLException e){
            throw new DataErrorException("Error agregando inscripcion");
        }
    }

    @Override
    public boolean delete(Integer numeroInscripcion) {
        String deleteQuery = "DELETE FROM inscripcion WHERE numero_inscripcion = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(deleteQuery);
            stm.setInt(1, numeroInscripcion);
            return stm.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataErrorException("Error borrando inscripcion");
        }
    }

    @Override
    public List<Inscripcion> findAll() {
        String findQuery = "SELECT numero_inscripcion," +
                "inscripcion.id_socio, " +
                "codigo_excursion, " +
                "nombre, " +
                "descripcion, " +
                "num_dias, " +
                "precio_inscripcion, " +
                "fecha " +
                "FROM inscripcion " +
                "JOIN excursion ON codigo = codigo_excursion " +
                "JOIN socio ON inscripcion.id_socio = socio.id_socio;";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            try (ResultSet results = stm.executeQuery()) {
                return readInscripciones(results);
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    @Override
    public Inscripcion update(Inscripcion inscripcion) {
        throw new DataErrorException("No es posible actualizar inscripciones");
    }

    public List<Inscripcion> findBySocio(int numeroSocio) {
        String findQuery = "SELECT numero_inscripcion," +
                "inscripcion.id_socio, " +
                "codigo_excursion, " +
                "nombre, " +
                "descripcion, " +
                "num_dias, " +
                "precio_inscripcion, " +
                "fecha " +
                "FROM inscripcion " +
                "JOIN socio ON inscripcion.id_socio = socio.id_socio " +
                "JOIN excursion ON codigo = codigo_excursion " +
                "WHERE inscripcion.id_socio = ?;";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setInt(1, numeroSocio);;
            try (ResultSet results = stm.executeQuery()) {
                return readInscripciones(results);
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    public List<Inscripcion> findByDate(LocalDate fechaInferior, LocalDate fechaSuperior) {
        String findQuery = "SELECT numero_inscripcion," +
                "inscripcion.id_socio, " +
                "codigo_excursion, " +
                "nombre, " +
                "descripcion, " +
                "num_dias, " +
                "precio_inscripcion, " +
                "fecha " +
                "FROM inscripcion " +
                "JOIN socio ON inscripcion.id_socio = socio.id_socio " +
                "JOIN excursion ON codigo = codigo_excursion " +
                "WHERE fecha BETWEEN ? AND ?;";
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setDate(1, Date.valueOf(fechaInferior));
            stm.setDate(2, Date.valueOf(fechaSuperior));
            try (ResultSet results = stm.executeQuery()) {
                return readInscripciones(results);
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    public List<Inscripcion> findByDateAndSocio(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio) {
        String findQuery = "SELECT numero_inscripcion," +
                "inscripcion.id_socio, " +
                "codigo_excursion, " +
                "nombre, " +
                "descripcion, " +
                "num_dias, " +
                "precio_inscripcion, " +
                "fecha " +
                "FROM inscripcion " +
                "JOIN socio ON inscripcion.id_socio = socio.id_socio " +
                "JOIN excursion ON codigo = codigo_excursion " +
                "WHERE inscripcion.id_socio = ? AND fecha BETWEEN ? AND ?;";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setInt(1, numeroSocio);
            stm.setDate(2, Date.valueOf(fechaInferior));
            stm.setDate(3, Date.valueOf(fechaSuperior));
            try (ResultSet results = stm.executeQuery()) {
                return readInscripciones(results);
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando inscripciones");
        }
    }

    private List<Inscripcion> readInscripciones(ResultSet results) throws SQLException {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        while (results.next()) {
            inscripciones.add(new Inscripcion(
                    results.getInt("numero_inscripcion"),
                    new Socio(
                            results.getString("nombre"),
                            results.getInt("id_socio")
                    ),
                    new Excursion(
                            results.getInt("num_dias"),
                            results.getDouble("precio_inscripcion"),
                            results.getString("codigo_excursion"),
                            results.getString("descripcion"),
                            results.getDate("fecha").toLocalDate()
                    )
            ));
        }
        return inscripciones;
    }
}