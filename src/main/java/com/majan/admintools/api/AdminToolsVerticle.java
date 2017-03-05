package com.majan.admintools.api;

import com.majan.admintools.api.common.JdbcUtil;
import com.majan.admintools.api.common.Metadata;
import com.majan.admintools.api.vehicle.owners.VehicleOwnerRoute;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;

import java.util.Date;

import static com.majan.admintools.api.common.Configurations.HTTP_PORT;

/**
 * Created by dilunika on 21/12/16.
 */
public class AdminToolsVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) throws Exception {

        Router router = Router.router(vertx);
        router.route("/").handler(rc -> {
            HttpServerResponse res = rc.response();
            res.putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(new Metadata("1.0.", new Date())));
        });

        JdbcUtil.initialize(vertx);
        VehicleOwnerRoute.initialize(router);

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        config().getInteger(HTTP_PORT, 8080),
                        http -> completeStartup(http, fut)
                );

    }

    private void completeStartup(AsyncResult<HttpServer> http, Future<Void> future) {

        if(http.succeeded()) {
            int port = config().getInteger(HTTP_PORT, 8080);
            System.out.println("Server started on port " + port);
            future.complete();
        } else {
            System.out.println("Failed to start server.");
            future.fail(http.cause());
        }
    }
}