package com.majan.admintools.api.common;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by dilunika on 3/01/17.
 */
public class ResponseUtil {

    public static <T> void generateResponse(RoutingContext routingContext, int statusCode, T body) {
        routingContext
                .response()
                .setStatusCode(statusCode)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(body));
    }
}
