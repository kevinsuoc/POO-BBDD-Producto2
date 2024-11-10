package dataroast.DAO;

import dataroast.modelo.*;
import dataroast.util.DataErrorException;
import dataroast.util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioEstandarDAO extends SocioDAO implements DAOInterface<SocioEstandar, Integer>  {

    @Override
    public SocioEstandar insert(SocioEstandar socio) {
        String insertQuery = "{CALL insertSocioEstandar(?, ?, ?, ?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(insertQuery);
            stm.setString(1, socio.getNombre());
            stm.setString(2, socio.getNif());
            stm.setString(3, socio.getSeguro().getTipoSeguro().name());
            stm.registerOutParameter(4, java.sql.Types.INTEGER);
            stm.execute();
            socio.setNumeroSocio(stm.getInt(4));
            return socio;
        } catch (SQLException e){
            throw new DataErrorException("Error insertando socio estandar");
        }
    }

    @Override
    public SocioEstandar find(Integer id) {
        String findQuery = "{CALL getSocioEstandarById(?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(findQuery);
            stm.setInt(1, id);
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()) {
                    return new SocioEstandar(
                            id,
                            results.getString("nif"),
                            results.getString("nombre"),
                            new Seguro(results.getDouble("precio"), TipoSeguro.valueOf(results.getString("seguro")))
                    );
                }
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando socio estandar");
        }
        return null;
    }

    @Override
    public List<SocioEstandar> findAll() {
        ArrayList<SocioEstandar> socios = new ArrayList<>();
        String findQuery = "{CALL getSociosEstandar()}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(findQuery);
            try (ResultSet results = stm.executeQuery()) {
                while (results.next()) {
                    socios.add(new SocioEstandar(
                            results.getInt("id"),
                            results.getString("nif"),
                            results.getString("nombre"),
                            new Seguro(results.getDouble("precio"), TipoSeguro.valueOf(results.getString("seguro")))
                    ));
                }
            }
            return socios;
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando socio estandar");
        }
    }

    @Override
    public SocioEstandar update(SocioEstandar socio) {
        String updateQuery = "{CALL updateSocioEstandar(?, ?, ?, ?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(updateQuery);
            stm.setInt(1, socio.getNumeroSocio());
            stm.setString(2, socio.getNif());
            stm.setString(3, socio.getNombre());
            stm.setString(4, socio.getSeguro().getTipoSeguro().name());
            stm.execute();
            return socio;
        } catch (SQLException e){
            throw new DataErrorException("Error actualizando socio estandar");
        }
    }
}