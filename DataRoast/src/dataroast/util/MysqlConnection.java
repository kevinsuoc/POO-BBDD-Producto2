package dataroast.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
        static String jdbcUrl = "jdbc:mysql://localhost:3306/senderosymontanas";
        static String username = "root";
        static String password = "root";

        public static Connection getConnection() throws SQLException {
                return DriverManager.getConnection(jdbcUrl, username, password);
        }
}
