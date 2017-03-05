/**
 * Created by dilunika on 4/03/17.
 */

import {RxHttpRequest as http} from 'rx-http-request'

const resourceUrl = 'http://localhost:8080/api/vehicleowners';

export const vehicleOwnerResource = {

    create: sendCreateVehicleOwnerRequest,
    findByNic: findByNic
};

function sendCreateVehicleOwnerRequest(vehicleOwner) {

    const options = {
        headers: {
            'X-REQUESTED-WITH': 'XMLHttpRequest',
            'Content-Type' : 'application/json',
            'Accept' : 'application/json'
        },
        body : vehicleOwner,
        json: true
    };

    return http.post(resourceUrl, options);
}

function findByNic(nic) {

    const options = {
        json: true
    };

    return http.get(resourceUrl + '?nic=' + nic, options);
}