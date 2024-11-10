      package dataroast.DAO;

      public class MysqlDAOFactory extends DAOFactory {
    public SocioEstandarDAO getSocioEstandarDAO(){
        return new SocioEstandarDAO();
    }

    public SocioFederadoDAO getSocioFederadoDAO() {return new SocioFederadoDAO();};

    public SocioInfantilDAO getSocioInfantilDAO() {return new SocioInfantilDAO();};

    public FederacionDAO getFederacionDAO() { return new FederacionDAO(); }

    public ExcursionDAO getExcursionDAO(){ return new ExcursionDAO(); }

    public InscripcionDAO getInscripcionDAO(){ return new InscripcionDAO(); }

    public SeguroDAO getSeguroDAO(){ return new SeguroDAO(); }

          public SocioDAO getSocioDAO() { return new SocioDAO();}

      }
