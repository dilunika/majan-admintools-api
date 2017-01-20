package com.majan.admintools.api.common;

import java.util.Date;

/**
 * Created by dilunika on 15/01/17.
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
