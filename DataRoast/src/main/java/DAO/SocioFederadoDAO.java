package DAO;

import modelo.Federacion;
import modelo.SocioFederado;
import util.DataErrorException;
import util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioFederadoDAO extends SocioDAO implements DAOInterface<SocioFederado, Integer> {
    @Override
    public SocioFederado insert(SocioFederado socio) {
        String insertQuery = "{CALL insertSocioFederado(?, ?, ?, ?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(insertQuery);
            stm.setString(1, socio.getNif());
            stm.setString(2, socio.getNombre());
            stm.setString(3, socio.getFederacion().getCodigo());
            stm.execute();
            stm.registerOutParameter(4, java.sql.Types.INTEGER);
            socio.setNumeroSocio(stm.getInt(4));
            return socio;
        } catch (SQLException e){
            throw new DataErrorException("Error agregando socio federado");
        }
    }

    @Override
    public SocioFederado find(Integer id) {
        String findQuery = "{CALL getSocioFederadoById(?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setInt(1, id);
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()) {
                    return new SocioFederado(
                            id,
                            results.getString("nif"),
                            results.getString("nombre"),
                            new Federacion(results.getString("codigo_federacion"), results.getString("nombre_federacion"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando socio federado");
        }
        return null;
    }

    @Override
    public List<SocioFederado> findAll() {
        ArrayList<SocioFederado> socios = new ArrayList<>();
        String findQuery = "{CALL getSociosFederados()}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(findQuery);
            try (ResultSet results = stm.executeQuery()) {
                while (results.next()) {
                    socios.add(new SocioFederado(
                            results.getInt("id"),
                            results.getString("nif"),
                            results.getString("nombre"),
                            new Federacion(results.getString("codigo_federacion"), results.getString("nombre_federacion"))
                    ));
                }
            }
            return socios;
        } catch (SQLException e) {
            throw new DataErrorException("Error busncaod socios federados");
        }
    }

    @Override
    public SocioFederado update(SocioFederado socio) {
        String insertQuery = "{CALL updateSocioFederado(?, ?, ?, ?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(insertQuery);
            stm.setInt(1, socio.getNumeroSocio());
            stm.setString(2, socio.getNif());
            stm.setString(3, socio.getNombre());
            stm.setString(4, socio.getFederacion().getCodigo());
            stm.execute();
            return socio;
        } catch (SQLException e){
            throw new DataErrorException("Error actualizando socio federado");
        }
    }
}
