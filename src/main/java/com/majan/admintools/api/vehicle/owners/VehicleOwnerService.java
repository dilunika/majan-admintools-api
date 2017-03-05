package com.majan.admintools.api.vehicle.owners;

import com.majan.admintools.api.common.SQLStatement;
import com.majan.admintools.api.domain.Address;
import com.majan.admintools.api.domain.ContactNumbers;
import com.majan.admintools.api.domain.VehicleOwner;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

import static com.majan.admintools.api.common.JdbcUtil.databaseClient;
import static com.majan.admintools.api.domain.VehicleOwner.aVehicleOwner;

/**
 * Created by dilunika on 4/01/17.
 */
public class VehicleOwnerService {


    public static Observable<Integer> insert(VehicleOwner vehicleOwner) {


        SQLStatement insert = new InsertStatement(vehicleOwner);

        return databaseClient()
                .getConnectionObservable()
                .flatMap(con -> con.updateWithParamsObservable(insert.sql(), insert.parameters()))
                .map(r -> r.getUpdated());
    }

    public static Observable<VehicleOwner> getVehicleOwnerById(long id) {

        SQLStatement query = new SearchByIDStatement(id);

        return databaseClient()
                .getConnectionObservable()
                .flatMap(con -> con.queryWithParamsObservable(query.sql(), query.parameters()))
                .map(r -> toVehicleOwner(r));
    }

    public static Observable<VehicleOwner> findByNic(String nicNumber) {

        SQLStatement query = new SearchByNicStatement(nicNumber);

        return databaseClient()
                .getConnectionObservable()
                .flatMap(con -> con.queryWithParamsObservable(query.sql(), query.parameters()))
                .map(r -> toVehicleOwner(r));
    }

    private static VehicleOwner toVehicleOwner(ResultSet resultSet) {

        List<JsonObject> rows = resultSet.getRows();
        List<VehicleOwner> vos = new ArrayList<>();

        for (JsonObject row: rows) {
            vos.add(mapToVehicleOwner(row));
        }

        return vos.get(0);
    }

    private static VehicleOwner mapToVehicleOwner(JsonObject row) {

        Long id = row.getLong("id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String nic = row.getString("nic");
        String email = row.getString("email");
        Address address = Json.decodeValue(row.getString("address"), Address.class);
        ContactNumbers contactNumbers = Json.decodeValue(row.getString("contact_numbers"), ContactNumbers.class);

        VehicleOwner vo = aVehicleOwner(firstName, lastName, contactNumbers, nic)
                            .withId(id)
                            .withAddress(address)
                            .withEmail(email)
                            .build();

        return vo;
    }


    static class InsertStatement implements SQLStatement{

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


        public String sql() {

            return "INSERT INTO admintools.vehicle_owners " +
                    "(first_name, last_name, nic, contact_numbers, email, address) " +
                    "VALUES(?, ?, ?, cast(? as json), ?, cast(? as json));";
        }

        public JsonArray parameters() {
            return params;
        }
    }

    static class SearchByIDStatement implements SQLStatement {

        JsonArray params;

        public SearchByIDStatement(long id) {
            this.params = new JsonArray().add(id);
        }

        @Override
        public String sql() {
            return "SELECT * FROM admintools.vehicle_owners WHERE id = ?";
        }

        @Override
        public JsonArray parameters() {
            return this.params;
        }
    }

    static class SearchByNicStatement implements SQLStatement {

        JsonArray params;

        public SearchByNicStatement(String nic) {
            this.params = new JsonArray().add(nic);
        }

        @Override
        public String sql() {
            return "SELECT * FROM admintools.vehicle_owners WHERE nic = ?";
        }

        @Override
        public JsonArray parameters() {
            return this.params;
        }
    }

}
