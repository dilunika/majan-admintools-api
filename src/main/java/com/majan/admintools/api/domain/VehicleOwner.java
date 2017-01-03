package com.majan.admintools.api.domain;

import com.majan.admintools.api.common.ValidationResult;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by dilunika on 31/12/16.
 */
public class VehicleOwner {

    private long id;
    private String firstName;
    private String lastName;
    private Address address;
    private ContactNumbers contactNumbers;
    private String email;


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public ContactNumbers getContactNumbers() {
        return contactNumbers;
    }

    public String getEmail() {
        return email;
    }

    private VehicleOwner() {
    }

    public static Builder aVehicleOwner(String firstName, String lastName, ContactNumbers contactNumbers) {
        return new Builder(firstName, lastName, contactNumbers);
    }

    public ValidationResult validate() {

        List<String> messages = new ArrayList<>();

        if(isNullOrEmpty(this.firstName)) {
            messages.add("Vehicle Owner should have a first name.");
        }

        if(isNullOrEmpty(this.lastName)) {
            messages.add("Vehicle Owner should have a last name.");
        }

        if(this.contactNumbers.noContactNumberIsSet()) {
            messages.add("Vehicle Owner should have at-least contact number.");
        }

        boolean isValid = messages.isEmpty() ? true : false;
        return new ValidationResult(isValid, messages);
    }

    public static class Builder {

        private long id;
        private String firstName;
        private String lastName;
        private Address address;
        private ContactNumbers contactNumbers;
        private String email;

        public Builder(String firstName, String lastName, ContactNumbers contactNumbers) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.contactNumbers = contactNumbers;
        }

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public VehicleOwner build() {

            VehicleOwner vo = new VehicleOwner();
            vo.id = this.id;
            vo.firstName = this.firstName;
            vo.lastName = this.lastName;
            vo.contactNumbers = this.contactNumbers;
            vo.email = this.email;
            vo.address = this.address;

            return vo;
        }
    }
}
