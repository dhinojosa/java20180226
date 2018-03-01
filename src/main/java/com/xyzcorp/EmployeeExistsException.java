package com.xyzcorp;

public class EmployeeExistsException extends Exception {
    public EmployeeExistsException(String message) {
        super(message);
    }
}
