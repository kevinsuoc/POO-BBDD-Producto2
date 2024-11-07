      package dataroast.DAO;

import dataroast.modelo.Excursion;
import dataroast.util.MysqlConnection;

public class MysqlDAOFactory extends DAOFactory {
    public SocioDAO getSocioDAO(){
        return new SocioDAO();
    }

    public FederacionDAO getFederacionDAO() { return new FederacionDAO(); }

    public ExcursionDAO getExcursionDAO(){ return new ExcursionDAO(); }

    public InscripcionDAO getInscripcionDAO(){ return new InscripcionDAO(); }

    public SeguroDAO getSeguroDAO(){ return new SeguroDAO(); }
}
