package com.example.superApp.payUrFren.exception;

/**
 * @deprecated Use NotFoundException instead.
 */
@Deprecated
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
