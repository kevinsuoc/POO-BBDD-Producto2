package dataroast.DAO;

import dataroast.util.DataErrorException;
import dataroast.util.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class  SocioDAO {
    public boolean delete(Integer id) {
        String deleteQuery = "DELETE FROM socio WHERE id_socio = ?";
        try (Connection con = MysqlConnection.getConnection()) {
            PreparedStatement stm = con.prepareStatement(deleteQuery);
            stm.setInt(1, id);
            return stm.executeUpdate() == 1;
        } catch (SQLException e){
            throw new DataErrorException("Error borrando socio");
        }
    }
}
