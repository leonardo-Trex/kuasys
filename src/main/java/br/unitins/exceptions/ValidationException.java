package br.unitins.exceptions;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int status;
    private final String title;
    private final Map<String, String> errors;

    public ValidationException(String detail) {
        this(detail, null);
    }

    public ValidationException(String detail, Map<String, String> errors) {
        super(detail);
        this.status = 400;
        this.title = "Validation Error";
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
