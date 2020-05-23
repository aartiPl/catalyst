package net.igsoft.catalyst.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.igsoft.catalyst.database.ngsql2o.Batch;
import net.igsoft.catalyst.database.ngsql2o.Connection;
import net.igsoft.catalyst.database.ngsql2o.DefaultBatch;
import net.igsoft.catalyst.database.ngsql2o.DefaultNgSql2o;
import net.igsoft.catalyst.database.ngsql2o.NgSql2o;
import net.igsoft.catalyst.database.ngsql2o.Query;
import net.igsoft.catalyst.database.ngsql2o.Transaction;
import net.igsoft.catalyst.database.ngsql2o.Update;

class NgSql2oApiTest {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    //@Test
    void api_SimpleConstructs() {
        NgSql2o sql2o = new DefaultNgSql2o();

        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("query");

            Iterator<User> userIterator = query.executeAndFetchLazy(USER_ROW_MAPPER);
            List<User> userList = query.executeAndFetch(USER_ROW_MAPPER);
            int userCount = query.executeScalar();
        }
    }

    //@Test
    void api_Update() {
        String sql = "INSERT INTO User(Name, Surname) VALUES (:Name, :Surname)";
        NgSql2o sql2o = new DefaultNgSql2o();

        try (Connection connection = sql2o.open()) {
            Update update = connection.createUpdate(sql);
            update.execute(USER_ROW_MAPPER);
        }
    }

    //@Test
    void api_Transaction() {
        String sql = "INSERT INTO User(Name, Surname) VALUES (:Name, :Surname)";
        NgSql2o sql2o = new DefaultNgSql2o();

        try (Transaction transaction = sql2o.open().transaction()) {
            Update update = transaction.createUpdate(sql);
            update.execute(USER_ROW_MAPPER);
        }
    }

    //@Test
    void api_BatchUpdate() {
        String sql = "INSERT INTO User(Name, Surname) VALUES (:Name, :Surname)";

        NgSql2o sql2o = new DefaultNgSql2o();
        List<User> userList = new ArrayList<>();

        try (Transaction transaction = sql2o.open().transaction()) {
            Batch batch = new DefaultBatch();

            for(User user : userList) {
                Update update = transaction.createUpdate(sql);
                update.execute(USER_ROW_MAPPER);

                batch.add(update);
            }

            batch.execute();
            transaction.commit();
        }
    }
}
