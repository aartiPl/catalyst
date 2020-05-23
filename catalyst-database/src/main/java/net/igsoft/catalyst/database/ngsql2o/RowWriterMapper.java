package net.igsoft.catalyst.database.ngsql2o;

public interface RowWriterMapper<T> {
    void write(RowWriter rowWriter, T object);
}
