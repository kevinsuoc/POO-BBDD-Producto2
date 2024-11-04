package dataroast.Test;

import dataroast.util.MysqlConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    @Test
    void testDatabase(){
        try {
            Connection connection = MysqlConnection.getConnection();
        } catch (SQLException e){
            throw new AssertionError("Database error: " + e);
        }
    }
}
