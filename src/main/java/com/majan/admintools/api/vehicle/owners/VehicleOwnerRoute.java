package com.majan.admintools.api.vehicle.owners;

import com.majan.admintools.api.common.ErrorResponse;
import com.majan.admintools.api.common.ValidationResult;
import com.majan.admintools.api.domain.VehicleOwner;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import static com.majan.admintools.api.common.ResponseUtil.generateResponse;

/**
 * Created by dilunika on 2/01/17.
 */
public class VehicleOwnerRoute {

    public static void initialize(Router router) {

        router.route("/api/vehicleowners*").handler(BodyHandler.create());
        router.post("/api/vehicleowners").handler(VehicleOwnerRoute::create);
    }

    public static void create(RoutingContext routingContext) {

        VehicleOwner vo = Json.decodeValue(routingContext.getBodyAsString(), VehicleOwner.class);

        if(vo == null) {

            ErrorResponse err = new ErrorResponse("Empty content is not allowed.");
            generateResponse(routingContext, 400, err);

        } else {

            ValidationResult validationResult = vo.validate();

            if(validationResult.isValid()) {
                VehicleOwnerService.insert(vo).subscribe(rowsUpdated -> {
                    if(rowsUpdated == 1){
                        generateResponse(routingContext, 201, vo);
                    } else {
                        generateResponse(routingContext, 500, "Failed to add Vehicle Owner. Could not persist.");
                    }
                }, err -> {
                    err.printStackTrace();
                    generateResponse(routingContext, 500, "Failed to add Vehicle Owner. Internal Error");

                });

            } else {
                ErrorResponse err = new ErrorResponse(validationResult.getErrorMessages());
                generateResponse(routingContext, 400, err);
            }
        }
    }

}
