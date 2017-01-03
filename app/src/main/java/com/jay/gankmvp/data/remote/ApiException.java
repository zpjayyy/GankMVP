package com.jay.gankmvp.data.remote;

/**
 * Created by jay on 16/12/30.
 */

public class ApiException extends Exception {

    public String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }
}
