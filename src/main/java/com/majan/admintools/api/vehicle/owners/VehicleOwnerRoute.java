package com.majan.admintools.api.vehicle.owners;

import com.majan.admintools.api.common.ErrorResponse;
import com.majan.admintools.api.common.ValidationResult;
import com.majan.admintools.api.domain.VehicleOwner;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import static com.majan.admintools.api.common.ResponseUtil.generateResponse;

/**
 * Created by dilunika on 2/01/17.
 */
public class VehicleOwnerRoute {

    public static void create(RoutingContext routingContext) {

        VehicleOwner vo = Json.decodeValue(routingContext.getBodyAsString(), VehicleOwner.class);

        if(vo == null) {

            ErrorResponse err = new ErrorResponse("Empty content is not allowed.");
            generateResponse(routingContext, 400, err);

        } else {

            ValidationResult validationResult = vo.validate();

            if(validationResult.isValid()) {
                generateResponse(routingContext, 201, vo);
            } else {
                ErrorResponse err = new ErrorResponse(validationResult.getErrorMessages());
                generateResponse(routingContext, 400, err);
            }
        }
    }




}
