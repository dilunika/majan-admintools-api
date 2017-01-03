package com.majan.admintools.api;

import java.util.Date;

/**
 * Created by dilunika on 27/12/16.
 */
public class Metadata {

    private String version;

    private Date currentDate;

    public Metadata(String version, Date currentDate) {
        this.version = version;
        this.currentDate = currentDate;
    }

    public String getVersion() {
        return version;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

}
