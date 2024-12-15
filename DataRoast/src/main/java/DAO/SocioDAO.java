package DAO;

import modelo.Socio;
import util.DataErrorException;
import util.HibernateUtil;

public class  SocioDAO {

    public boolean delete(Integer id) {
        try {
            return HibernateUtil.getSessionFactory().fromTransaction(session -> {
                Socio socio = session.find(Socio.class, id);
                if (socio != null)
                    session.remove(socio);
                return (socio != null);
            });
        } catch (Exception e){
            throw new DataErrorException("No se pudo eliminar el socio");
        }
    }
}
