package com.majan.admintools.api.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilunika on 2/01/17.
 */
public class ErrorResponse {

    private List<String> errorMessages = new ArrayList<>();

    public ErrorResponse(String message) {
        errorMessages.add(message);
    }

    public ErrorResponse(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
