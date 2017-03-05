package com.majan.admintools.api.common;

import io.vertx.core.json.JsonArray;

/**
 * Created by dilunika on 16/02/17.
 */
public interface SQLStatement {

    String sql();

    JsonArray parameters();
}
