package com.majan.admintools.api.domain;

import com.majan.admintools.api.common.ValidationResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.majan.admintools.api.domain.ContactNumbers.newNumbersList;
import static com.majan.admintools.api.domain.VehicleOwner.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 * Created by dilunika on 31/12/16.
 */
@DisplayName("Vehicle Owner")
class VehicleOwnerTest {

    @Test
    @DisplayName("Should be created with minimum information (First Name, Last Name and Contact Number).")
    public void shouldBeCreatedWithMandatoryFieldsAreGiven() {

        ContactNumbers cn = newNumbersList()
                                .withMobileNumber("0772322323")
                                .build();

        VehicleOwner vo = aVehicleOwner("Nimal", "Perera", cn).build();

        assertThat(vo, notNullValue());
        assertThat(vo.getFirstName(), is("Nimal"));
    }

    @Test
    @DisplayName("Should not have null or empty first name.")
    public void shouldBeInvalidIfFirstNameIsNullOrEmpty() {

        ContactNumbers cn = newNumbersList()
                                .withMobileNumber("0772322323")
                                .build();

        VehicleOwner vo = aVehicleOwner("", "Perera", cn).build();

        ValidationResult validationResult = vo.validate();
        assertThat(validationResult.isValid(), is(false));
        assertThat(validationResult.getErrorMessages().size(), is(1));
    }

    @Test
    @DisplayName("Should not have null or empty last name.")
    public void shouldBeInvalidIfLastNameIsNullOrEmpty() {

        ContactNumbers cn = newNumbersList()
                                .withHomeNumber("011234343")
                                .build();

        VehicleOwner vo = aVehicleOwner("Amal", "", cn).build();

        ValidationResult validationResult = vo.validate();
        assertThat(validationResult.isValid(), is(false));
        assertThat(validationResult.getErrorMessages().size(), is(1));
    }

    @Test
    @DisplayName("Should have at-least single contact number.")
    public void shouldHaveAtLeastSingleContactNumber() {

        ContactNumbers cn = newNumbersList().build();

        VehicleOwner vo = aVehicleOwner("Amal", "Perera", cn).build();

        ValidationResult validationResult = vo.validate();
        assertThat(validationResult.isValid(), is(false));
        assertThat(validationResult.getErrorMessages().size(), is(1));
    }
}