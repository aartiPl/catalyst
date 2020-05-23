package net.igsoft.catalyst.database.ngsql2o;

import java.io.Closeable;

public interface Batch {
    void add(Update update);
    void execute();
}
