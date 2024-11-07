package dataroast.DAO;

import dataroast.modelo.Seguro;
import dataroast.modelo.TipoSeguro;
import dataroast.util.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO implements DAOInterface<Seguro, TipoSeguro>{
    @Override
    public Seguro insert(Seguro entity) {
        throw new DAOException("No es posible agregar nuevos seguros");
    }

    @Override
    public Seguro find(TipoSeguro tipoSeguro) {
        String findQuery = "SELECT * FROM seguro WHERE nombre = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setString(1, tipoSeguro.name());
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()){
                    return new Seguro(results.getDouble("precio"), TipoSeguro.valueOf(results.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error buscando federacion: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Seguro> findAll() {
        String findQuery = "SELECT * FROM seguro";
        List<Seguro> seguros = new ArrayList<>();

        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            try (ResultSet results = stm.executeQuery()){
                while (results.next()){
                    seguros.add(new Seguro(results.getDouble("precio"), TipoSeguro.valueOf(results.getString("nombre"))));
                }
            }
            return seguros;
        } catch (SQLException e) {
            throw new DAOException("Error buscando seguros: " + e.getMessage());
        }
    }

    @Override
    public Seguro update(Seguro entity) {
        throw new DAOException("No es posible actualizar seguros");
    }

    @Override
    public boolean delete(TipoSeguro tipoSeguro) {
        throw new DAOException("No es posible borrar seguros");
    }
}
