package net.igsoft.catalyst.database;

import net.igsoft.catalyst.database.ngsql2o.RowMapper;
import net.igsoft.catalyst.database.ngsql2o.RowReader;
import net.igsoft.catalyst.database.ngsql2o.RowWriter;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User read(RowReader rowView) {
        return new User(rowView.getString("Name"), rowView.getString("Surname"));
    }

    @Override
    public void write(RowWriter rowWriter, User object) {
        rowWriter.put("Name", object.getName()).put("Surname", object.getSurname());
    }
}
