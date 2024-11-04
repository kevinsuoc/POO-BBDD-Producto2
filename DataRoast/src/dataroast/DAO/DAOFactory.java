package dataroast.DAO;

public abstract class DAOFactory  {
    public abstract SocioDAO getSocioDAO();

    public static DAOFactory getDAOFactory(){
        return new MysqlDAOFactory();
    }
}
