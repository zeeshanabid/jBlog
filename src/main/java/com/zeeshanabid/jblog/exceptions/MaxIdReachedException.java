package com.zeeshanabid.jblog.exceptions;

public class MaxIdReachedException extends Exception {
    public MaxIdReachedException() {
        super("Maximum value of id has been reached");
    }
}
