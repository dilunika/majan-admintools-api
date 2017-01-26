/**
 * Created by dilunika on 21/01/17.
 */
var frisby = require('frisby');

var vehicleOwner = {
    firstName: 'Kasun',
    lastName: 'Dilunika',
    nic: '843324245454',
    contactNumbers: {
        mobileNumber: '0774544454'
    }
};

frisby.create('Add new Vehicle Owner to the System.')
    .post('http://localhost:8080/api/vehicleowners', vehicleOwner, {json: true})
    .expectStatus(201)
    .toss()
