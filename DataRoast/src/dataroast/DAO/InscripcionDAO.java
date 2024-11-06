package dataroast.DAO;

import dataroast.modelo.Inscripcion;
import dataroast.modelo.Socio;
import dataroast.modelo.Excursion;
import dataroast.util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO implements DAOInterface<Inscripcion, Integer> {

    @Override
    public Inscripcion find(Integer numeroInscripcion) {
        String findQuery = "SELECT * FROM inscripcion WHERE numero_inscripcion = ?";
        String sociosQuery = "SELECT socio_id FROM inscripcion_socio WHERE numero_inscripcion = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setInt(1, numeroInscripcion);

            try (ResultSet results = stm.executeQuery()) {
                if (results.next()) {
                    String excursionCodigo = results.getString("excursion_codigo");
                    Excursion excursion = new ExcursionDAO().find(excursionCodigo);

                    // Obtener los socios asociados a esta inscripción
                    PreparedStatement stmSocios = con.prepareStatement(sociosQuery);
                    stmSocios.setInt(1, numeroInscripcion);
                    ResultSet rsSocios = stmSocios.executeQuery();

                    List<Socio> socios = new ArrayList<>();
                    while (rsSocios.next()) {
                        Socio socio = new SocioDAO().find(rsSocios.getString("socio_id"));
                        if (socio != null) socios.add(socio);
                    }

                    return new Inscripcion(numeroInscripcion, socios, excursion);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error buscando inscripcion: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Inscripcion insert(Inscripcion inscripcion) {
        String insertQuery = "INSERT INTO inscripcion (numero_inscripcion, excursion_codigo) VALUES (?, ?)";
        String insertSociosQuery = "INSERT INTO inscripcion_socio (numero_inscripcion, socio_id) VALUES (?, ?)";

        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(insertQuery);
            stm.setInt(1, inscripcion.getNumeroInscripcion());
            stm.setString(2, inscripcion.getExcursion().getCodigo());

            if (stm.executeUpdate() == 1) {
                // Insertar cada socio en la tabla intermedia
                for (Socio socio : inscripcion.getSocios()) {
                    PreparedStatement stmSocio = con.prepareStatement(insertSociosQuery);
                    stmSocio.setInt(1, inscripcion.getNumeroInscripcion());
                    stmSocio.setString(2, socio.getNumeroSocio());
                    stmSocio.executeUpdate();
                }
                return inscripcion;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error insertando inscripcion: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer numeroInscripcion) {
        String deleteQuery = "DELETE FROM inscripcion WHERE numero_inscripcion = ?";
        String deleteSociosQuery = "DELETE FROM inscripcion_socio WHERE numero_inscripcion = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            // Borrar los socios asociados en la tabla intermedia
            PreparedStatement stmSocios = con.prepareStatement(deleteSociosQuery);
            stmSocios.setInt(1, numeroInscripcion);
            stmSocios.executeUpdate();

            // Borrar la inscripción
            PreparedStatement stm = con.prepareStatement(deleteQuery);
            stm.setInt(1, numeroInscripcion);
            return stm.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DAOException("Error borrando inscripcion: " + e.getMessage());
        }
    }

    @Override
    public List<Inscripcion> findAll() {
        String findAllQuery = "SELECT * FROM inscripcion";
        String sociosQuery = "SELECT socio_id FROM inscripcion_socio WHERE numero_inscripcion = ?";
        List<Inscripcion> inscripciones = new ArrayList<>();

        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findAllQuery);
            try (ResultSet results = stm.executeQuery()) {
                while (results.next()) {
                    int numeroInscripcion = results.getInt("numero_inscripcion");
                    String excursionCodigo = results.getString("excursion_codigo");
                    Excursion excursion = new ExcursionDAO().find(excursionCodigo);

                    // Obtener los socios asociados a esta inscripción
                    PreparedStatement stmSocios = con.prepareStatement(sociosQuery);
                    stmSocios.setInt(1, numeroInscripcion);
                    ResultSet rsSocios = stmSocios.executeQuery();

                    List<Socio> socios = new ArrayList<>();
                    while (rsSocios.next()) {
                        Socio socio = new SocioDAO().find(rsSocios.getString("socio_id"));
                        if (socio != null) socios.add(socio);
                    }

                    inscripciones.add(new Inscripcion(numeroInscripcion, socios, excursion));
                }
            }
            return inscripciones;
        } catch (SQLException e) {
            throw new DAOException("Error buscando todas las inscripciones: " + e.getMessage());
        }
    }

    @Override
    public Inscripcion update(Inscripcion inscripcion) {
        String updateQuery = "UPDATE inscripcion SET excursion_codigo = ? WHERE numero_inscripcion = ?";
        String deleteSociosQuery = "DELETE FROM inscripcion_socio WHERE numero_inscripcion = ?";
        String insertSociosQuery = "INSERT INTO inscripcion_socio (numero_inscripcion, socio_id) VALUES (?, ?)";

        try (Connection con = MysqlConnection.getConnection()) {
            // Actualizar la inscripción
            PreparedStatement stm = con.prepareStatement(updateQuery);
            stm.setString(1, inscripcion.getExcursion().getCodigo());
            stm.setInt(2, inscripcion.getNumeroInscripcion());
            stm.executeUpdate();

            // Borrar los socios existentes en la tabla intermedia
            PreparedStatement stmDelete = con.prepareStatement(deleteSociosQuery);
            stmDelete.setInt(1, inscripcion.getNumeroInscripcion());
            stmDelete.executeUpdate();

            // Insertar los socios actualizados en la tabla intermedia
            for (Socio socio : inscripcion.getSocios()) {
                PreparedStatement stmInsert = con.prepareStatement(insertSociosQuery);
                stmInsert.setInt(1, inscripcion.getNumeroInscripcion());
                stmInsert.setString(2, socio.getNumeroSocio());
                stmInsert.executeUpdate();
            }
            return inscripcion;
        } catch (SQLException e) {
            throw new DAOException("Error actualizando inscripcion: " + e.getMessage());
        }
    }
}