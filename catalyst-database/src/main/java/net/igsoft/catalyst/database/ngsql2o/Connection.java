package net.igsoft.catalyst.database.ngsql2o;

public interface Connection {
    Connection transaction();
    Connection commit();
    Connection rollback();
    Query createQuery(String sql);
}
