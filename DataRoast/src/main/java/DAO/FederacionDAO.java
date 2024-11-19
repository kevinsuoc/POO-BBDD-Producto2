package DAO;

import modelo.Federacion;
import util.DataErrorException;
import util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FederacionDAO implements DAOInterface<Federacion, String> {
    public FederacionDAO() {
    }

    @Override
    public Federacion find(String codigo) {
        String findQuery = "SELECT * FROM federacion WHERE codigo = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setString(1, codigo);
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()){
                    return new Federacion(results.getString("codigo"), results.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando federacion");
        }
        return null;
    }

    @Override
    public List<Federacion> findAll() {
        String findQuery = "SELECT * FROM federacion";
        List<Federacion> federaciones = new ArrayList<>();

        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            try (ResultSet results = stm.executeQuery()){
                while (results.next()){
                    federaciones.add(new Federacion(results.getString("codigo"), results.getString("nombre")));
                }
            }
            return federaciones;
        } catch (SQLException e) {
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
        String deleteQuery = "DELETE FROM federacion WHERE codigo = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(deleteQuery);
            stm.setString(1, codigo);
            return stm.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataErrorException("Error borrando federacion");
        }
    }

    @Override
    public Federacion insert(Federacion federacion) {
        String insertQuery = "INSERT INTO federacion (codigo, nombre) VALUES (?, ?)";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(insertQuery);
            stm.setString(1, federacion.getCodigo());
            stm.setString(2, federacion.getNombre());
            if (stm.executeUpdate() == 1)
                return federacion;
            else
                return null;
        } catch (SQLException e) {
            throw new DataErrorException("Error agregando federacion");
        }
    }
}
