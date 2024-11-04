package dataroast.Test;

import dataroast.controlador.Controlador;
import dataroast.util.MysqlConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    private Connection connection;

    @BeforeEach
    void createConnectio() throws SQLException{
        // Crear una conexion y poner autocommit a falso para que no se guarden los cambios
        connection = MysqlConnection.getConnection();
        connection.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Hacer rollback y cerrar la conexion al finalizar
        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    void testDatabase(){
        String codigo = "abc";
        String nombre = "Una federacion de prueba";
        String selectFederacionQuery = "SELECT * FROM federacion WHERE codigo = ?";
        String addFederacionQuery = "INSERT INTO federacion VALUES (?, ?)";

        try {
            // Preparar statement ADD
            PreparedStatement stm = connection.prepareStatement(addFederacionQuery);
            // Poner variables
            stm.setString(1, codigo);
            stm.setString(2, nombre);
            // Ejecutar
            stm.executeUpdate();

            // Statement SELECT
            stm = connection.prepareStatement(selectFederacionQuery);
            stm.setString(1, codigo);
            // Obtener resltados
            ResultSet results = stm.executeQuery();

            // si hay resultados... si no error
            if (results.next()){
                // Comparar los resultados
                assertEquals(codigo, results.getString("codigo"), "El codigo no corresponde");
                assertEquals(nombre, results.getString("nombre"), "El nombre no corresponde");
            } else {
                throw new AssertionError("No se ha podido insertar y seleccionar a la base de datos");
            }
        } catch (SQLException e){
            throw new AssertionError("Database error: " + e);
        }
    }
}
