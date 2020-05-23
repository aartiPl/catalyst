package net.igsoft.catalyst.database.ngsql2o;

import java.io.Closeable;
import java.sql.BatchUpdateException;

public interface Transaction extends Closeable {
    Transaction commit();
    Transaction rollback();

    Query createQuery(String sql);
    Update createUpdate(String sql);

    @Override
    void close();
}
