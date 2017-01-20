package com.majan.admintools.api.common;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.jdbc.JDBCClient;

/**
 * Created by dilunika on 7/01/17.
 */
public class JdbcUtil {

    private static JDBCClient jdbcClient;

    public static void initialize(Vertx vertx) {

        if(jdbcClient == null) {
            final JsonObject jdbcConfig =  new JsonObject()
                    .put("url", "jdbc:postgresql://localhost/majan")
                    .put("driver_class", "org.postgresql.Driver")
                    .put("max_pool_size", 30);
            jdbcClient = JDBCClient.createShared(io.vertx.rxjava.core.Vertx.newInstance(vertx),jdbcConfig);

        }
    }

    public static JDBCClient databaseClient() {

        return jdbcClient;
    }
}
