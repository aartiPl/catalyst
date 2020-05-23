package net.igsoft.catalyst.database.ngsql2o;

import java.util.Iterator;
import java.util.List;

public interface Query {
    <T> Iterator<T> executeAndFetchLazy(RowReaderMapper<T> rowReaderMapper);
    <T> List<T> executeAndFetch(RowReaderMapper<T> rowReaderMapper);


    <T> T executeScalar();
}
