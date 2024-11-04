package dataroast.Test;

import dataroast.util.MysqlConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTest {

    @Test
    void testDatabase(){
        String codigo = "abc";
        String nombre = "Una federacion de prueba";
        String selectFederacionQuery = "SELECT * FROM federacion WHERE codigo = '" + codigo + "' ;";
        String addFederacionQuery = "INSERT INTO federacion VALUES ('"+ codigo  +"', '"+ nombre +"');";

        try {
            Connection connection = MysqlConnection.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement(addFederacionQuery);
            stm.executeUpdate();

            stm = connection.prepareStatement(selectFederacionQuery);
            ResultSet results = stm.executeQuery();
            if (results.next()){
                assert results.getString("codigo").equals(codigo): "Codigo incorrecto";
                assert results.getString("nombre").equals(nombre): "Nombre incorrecto";
            } else {
                throw new AssertionError("No se ha podido insertar y seleccionar a la base de datos");
            }

            connection.rollback();
        } catch (SQLException e){
            throw new AssertionError("Database error: " + e);
        }
    }
}
