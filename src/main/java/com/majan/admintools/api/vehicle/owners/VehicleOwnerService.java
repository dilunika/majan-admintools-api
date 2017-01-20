package com.majan.admintools.api.vehicle.owners;

import com.majan.admintools.api.domain.Address;
import com.majan.admintools.api.domain.VehicleOwner;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import rx.Observable;

import static com.majan.admintools.api.common.JdbcUtil.databaseClient;

/**
 * Created by dilunika on 4/01/17.
 */
public class VehicleOwnerService {


    public static Observable<Integer> insert(VehicleOwner vehicleOwner) {


        InsertStatement insert = new InsertStatement(vehicleOwner);

        return databaseClient()
                .getConnectionObservable()
                .flatMap(con -> con.updateWithParamsObservable(insert.sql(), insert.parameters()))
                .map(r -> r.getUpdated());
    }

    static class InsertStatement {

        JsonArray params;

        InsertStatement(VehicleOwner vo) {

            String email = vo.getEmail() == null ? "" : vo.getEmail();
            String address = vo.getAddress() == null ? Json.encode(new Address()) : Json.encode(vo.getAddress());

            this.params = new JsonArray()
                    .add(vo.getFirstName())
                    .add(vo.getLastName())
                    .add(vo.getNic())
                    .add(Json.encode(vo.getContactNumbers()))
                    .add(email)
                    .add(address);
        }


        String sql() {

            return "INSERT INTO admintools.vehicle_owners " +
                    "(first_name, last_name, nic, contact_numbers, email, address) " +
                    "VALUES(?, ?, ?, cast(? as json), ?, cast(? as json));";
        }

        JsonArray parameters() {
            return params;
        }
    }

}
