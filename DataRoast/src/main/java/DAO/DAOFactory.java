package DAO;

public abstract class DAOFactory  {
    public static DAOFactory getDAOFactory(){
        return new MysqlDAOFactory();
    }
}
