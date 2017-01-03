package com.majan.admintools.api.common;

import java.util.List;

/**
 * Created by dilunika on 2/01/17.
 */
public class ValidationResult {

    private boolean isValid;

    private List<String> errorMessages;

    public ValidationResult(boolean isValid, List<String> messages) {

        this.isValid = isValid;
        this.errorMessages = messages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public boolean isValid() {
        return isValid;
    }
}
