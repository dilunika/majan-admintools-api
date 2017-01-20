package com.majan.admintools.api.domain;

import com.google.common.base.Strings;

import static com.google.common.base.Strings.*;

/**
 * Created by dilunika on 31/12/16.
 */
public class ContactNumbers {

    private String mobileNumber;
    private String homeNumber;
    private String officeNumber;

    public ContactNumbers() {
    }

    public static Builder newNumbersList() {
        return new Builder();
    }

    public boolean noContactNumberIsSet() {

        return isNullOrEmpty(this.mobileNumber)
                && isNullOrEmpty(this.homeNumber)
                && isNullOrEmpty(this.officeNumber);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public static class Builder {

        private String mobileNumber;
        private String homeNumber;
        private String officeNumber;

        Builder(){

        }

        public Builder withMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder withHomeNumber(String homeNumber) {
            this.homeNumber = homeNumber;
            return this;
        }

        public Builder withOfficeNumber(String officeNumber) {
            this.officeNumber = officeNumber;
            return this;
        }

        public ContactNumbers build() {

            ContactNumbers cn = new ContactNumbers();
            cn.mobileNumber = this.mobileNumber;
            cn.homeNumber = this.homeNumber;
            cn.officeNumber = this.officeNumber;

            return cn;
        }
    }
}
