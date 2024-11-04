package dataroast.DAO;

import dataroast.util.MysqlConnection;

public class MysqlDAOFactory extends DAOFactory {
    public static final String DRIVER = "";
    public static final String DBURL = "";

    public static MysqlConnection createConnection(){
        return new MysqlConnection();
    }

    public SocioDAO getSocioDAO(){
        return new SocioDAO();
    }
}
