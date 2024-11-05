package dataroast.DAO;

import java.util.List;

public interface DAOInterface<T, ID> {
    T insert(T entity);
    T find(ID id);
    List<T> findAll();
    T update(T entity);
    boolean delete(ID id);
}
