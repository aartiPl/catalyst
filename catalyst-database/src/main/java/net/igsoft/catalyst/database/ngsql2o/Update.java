package net.igsoft.catalyst.database.ngsql2o;

public interface Update {
    <T> Update execute(RowWriterMapper<T> rowWriterMapper);
    void executeBatch();
}
