package com.zeeshanabid.jblog.exceptions;

import javax.ws.rs.core.Response.Status;
import java.io.Serializable;

public class MaxIdReachedException extends BlogException implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int statusCode        = Status.INTERNAL_SERVER_ERROR.getStatusCode();

    public MaxIdReachedException() {
        super("Maximum value of id has been reached");
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
