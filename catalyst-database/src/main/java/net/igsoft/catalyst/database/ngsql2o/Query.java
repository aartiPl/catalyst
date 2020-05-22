package net.igsoft.catalyst.database.ngsql2o;

import java.util.Iterator;

public interface Query {
    <T> Iterator<T> executeAndFetchLazy(RowViewHandler<T> rowHandler);
    <T> T executeScalar();
}
