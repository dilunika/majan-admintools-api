package com.majan.admintools.api.common;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.jdbc.JDBCClient;

import static com.majan.admintools.api.common.Configurations.*;

/**
 * Created by dilunika on 7/01/17.
 */
public class JdbcUtil {

    private static JDBCClient jdbcClient;

    public static void initialize(Vertx vertx) {

        JsonObject config = vertx.getOrCreateContext().config();

        if(jdbcClient == null) {
            final JsonObject jdbcConfig =  new JsonObject()
                    .put("url", config.getString(DB_URL, "jdbc:postgresql://localhost/majan"))
                    .put("driver_class", config.getString(DB_DRIVER_CLASS, "org.postgresql.Driver"))
                    .put("max_pool_size", config.getInteger(DB_MAX_POOL_SIZE, 30));

            jdbcClient = JDBCClient.createShared(io.vertx.rxjava.core.Vertx.newInstance(vertx),jdbcConfig);

        }
    }

    public static JDBCClient databaseClient() {

        return jdbcClient;
    }
}
