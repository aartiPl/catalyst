package net.igsoft.catalyst.database.ngsql2o;

public interface RowViewHandler<T> {
    T handle(RowView rowView);
}
