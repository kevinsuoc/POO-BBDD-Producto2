      package dataroast.DAO;

import dataroast.util.MysqlConnection;

public class MysqlDAOFactory extends DAOFactory {
    public SocioDAO getSocioDAO(){
        return new SocioDAO();
    }

    public FederacionDAO getFederacionDAO() { return new FederacionDAO(); }
}
