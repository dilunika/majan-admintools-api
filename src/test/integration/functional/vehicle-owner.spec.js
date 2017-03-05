/**
 * Created by dilunika on 21/01/17.
 */

import test from 'tape';
import {newVehicleOwner} from '../util/data-generator';
import { vehicleOwnerResource } from '../util/api-consumer'

test('Create new Vehicle Owner', assert => {

    const vo = newVehicleOwner();
    console.log('Generated Vehicle Owner : {}', JSON.stringify(vo));

    vehicleOwnerResource.create(vo).subscribe(data => {

        assert.equal(data.response.statusCode, 201,'Response status code should be 201(Created).');

        vehicleOwnerResource.findByNic(vo.nic).subscribe(data => {
            assert.equal(data.response.statusCode, 200,'Response status code should be 200(Ok).');
            assert.equal(data.body.nic, vo.nic, 'Returned resource should have same NIC');
            assert.end();

        });
        
    });

});


// var frisby = require('frisby');
//
// var api = 'http://localhost:8080/api/vehicleowners';
//
// var vehicleOwner = {
//     firstName: 'Kasun',
//     lastName: 'Dilunika',
//     nic: randomNicNumber(),
//     contactNumbers: {
//         mobileNumber: '0774544454'
//     }
// };
//
//
// frisby.create('Add new Vehicle Owner to the System.')
//     .post(api, vehicleOwner, {json: true})
//     .expectStatus(201)
//     .after(function (err, res, body) {
//         getVehicleOwnerByNic(vehicleOwner.nic)
//     })
//     .toss();
//
// function getVehicleOwnerByNic(nic) {
//
//     var url = api + '?nic=' + nic;
//     frisby.create('Get Vehicle Owner by NIC')
//         .get(url)
//         .expectStatus(200)
//         .expectJSON({
//             nic: nic
//         })
//         .toss()
// }
//
// function randomNicNumber() {
//
//     return Math.random().toString().slice(2,11) + 'V';
// }