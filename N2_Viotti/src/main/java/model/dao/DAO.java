package model.dao;

import java.sql.SQLException;
import java.util.Collection;
import javafx.print.Collation;

public interface DAO <T> {
    
    public boolean insere(T model) throws SQLException;
    public boolean remove(T model) throws SQLException;
    public boolean altera(T model) throws SQLException;
    public T buscaID(T model) throws SQLException;
    public Collection<T> lista(String criterio) throws SQLException;
}
