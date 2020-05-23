package net.igsoft.catalyst.database.ngsql2o;

public interface RowReaderMapper<T> {
    T read(RowReader rowReader);
}
