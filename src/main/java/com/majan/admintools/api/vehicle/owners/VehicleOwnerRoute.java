package com.majan.admintools.api.vehicle.owners;

import com.majan.admintools.api.common.ErrorResponse;
import com.majan.admintools.api.common.ValidationResult;
import com.majan.admintools.api.domain.VehicleOwner;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.Optional;

import static com.majan.admintools.api.common.ResponseUtil.generateResponse;

/**
 * Created by dilunika on 2/01/17.
 */
public class VehicleOwnerRoute {

    private static final Logger LOGGER = LoggerFactory.getLogger("Vehicle Owner Route");

    public static void initialize(Router router) {

        router.route("/api/vehicleowners*").handler(BodyHandler.create());
        router.post("/api/vehicleowners").handler(VehicleOwnerRoute::create);
        router.get("/api/vehicleowners").handler(VehicleOwnerRoute::list);
    }

    public static void create(RoutingContext routingContext) {

        VehicleOwner vo = Json.decodeValue(routingContext.getBodyAsString(), VehicleOwner.class);

        if(vo == null) {

            ErrorResponse err = new ErrorResponse("Empty content is not allowed.");
            generateResponse(routingContext, 400, err);

            LOGGER.info("Vehicle Owner is empty. Cannot create new one.");

        } else {

            ValidationResult validationResult = vo.validate();

            if(validationResult.isValid()) {
                VehicleOwnerService.insert(vo).subscribe(rowsUpdated -> {

                    if(rowsUpdated == 1){

                        generateResponse(routingContext, 201, null);
                        LOGGER.info("New Vehicle Owner is created successfully.");

                    } else {
                        generateResponse(routingContext, 500, "Failed to add Vehicle Owner. Could not persist.");
                    }
                }, err -> {
                    err.printStackTrace();
                    String message = "Failed to add Vehicle Owner. Internal Error";
                    generateResponse(routingContext, 500, message);
                    LOGGER.warn(message + " -> " + err.getMessage());

                });

            } else {
                ErrorResponse err = new ErrorResponse(validationResult.getErrorMessages());
                generateResponse(routingContext, 400, err);
            }
        }
    }

    public static void list(RoutingContext routingContext) {

        Optional<String> nic = Optional.ofNullable(routingContext.request().getParam("nic"));

        LOGGER.info("Vehicle Owner search " + routingContext.request().absoluteURI());

        if(nic.isPresent()) {

            String nicNumber = nic.get();
            VehicleOwnerService.findByNic(nicNumber).subscribe(vo -> {

                if(vo == null) {
                    generateResponse(routingContext, 404, null);
                } else  {
                    generateResponse(routingContext, 200, vo);
                }
            });
        }
    }

}
