/**
 * Created by dilunika on 5/03/17.
 */

import * as faker from 'faker';

export function newVehicleOwner() {

    const vehicleOwner = {
        firstName: faker.name.firstName(),
        lastName: faker.name.lastName(),
        nic: randomNicNumber(),
        contactNumbers: {
            mobileNumber: randomMobileNumber()
        }
    };

    return vehicleOwner;
}

function randomNicNumber() {

    return Math.random().toString().slice(2,11) + 'V';
}

function randomMobileNumber() {

    const suffix = Math.random().toString().slice(2,6);

    return  '0224' + suffix;
}