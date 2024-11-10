package dataroast.DAO;

import dataroast.modelo.*;
import dataroast.util.DataErrorException;
import dataroast.util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioInfantilDAO extends SocioDAO implements DAOInterface<SocioInfantil, Integer> {
    @Override
    public SocioInfantil insert(SocioInfantil socio) {
        String insertQuery = "{CALL insertSocioInfantil(?, ?, ?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(insertQuery);
            stm.setString(1, socio.getNombre());
            stm.setInt(2, socio.getNumeroSocioTutor());
            stm.registerOutParameter(3, java.sql.Types.INTEGER);
            stm.execute();
            socio.setNumeroSocio(stm.getInt(3));
            return socio;
        } catch (SQLException e){
            throw new DataErrorException("Error agregando socio infantil a la base de datos");
        }
    }

    @Override
    public SocioInfantil find(Integer id) {
        String findQuery = "{CALL getSocioInfantilById(?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(findQuery);
            stm.setInt(1, id);
            try (ResultSet results = stm.executeQuery()) {
                if (results.next()) {
                    return new SocioInfantil(id, results.getString("nombre"), results.getInt("tutor"));
                }
            }
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando socio infantil en la base de datos");
        }
        return null;
    }

    @Override
    public List<SocioInfantil> findAll() {
        ArrayList<SocioInfantil> socios = new ArrayList<>();
        String findQuery = "{CALL getSociosInfantiles()}";
        try (Connection con = MysqlConnection.getConnection()) {
            CallableStatement stm = con.prepareCall(findQuery);
            try (ResultSet results = stm.executeQuery()) {
                while (results.next()) {
                    socios.add(new SocioInfantil(results.getInt("id"),
                            results.getString("nombre"),
                            results.getInt("tutor")));
                }
            }
            return socios;
        } catch (SQLException e) {
            throw new DataErrorException("Error buscando socios infantiles en la base de datos");
        }
    }

    @Override
    public SocioInfantil update(SocioInfantil socio) {
        String updateQuery = "{CALL updateSocioInfantil(?, ?, ?)}";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(updateQuery);
            stm.setInt(1, socio.getNumeroSocio());
            stm.setString(2, socio.getNombre());
            stm.setInt(3, socio.getNumeroSocioTutor());
            stm.execute();
            return socio;
        } catch (SQLException e){
            throw new DataErrorException("Error actualizando socio infantil en la base de datos");
        }
    }
}
