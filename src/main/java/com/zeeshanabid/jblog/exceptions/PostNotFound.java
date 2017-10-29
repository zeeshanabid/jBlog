package com.zeeshanabid.jblog.exceptions;

import javax.ws.rs.core.Response.Status;
import java.io.Serializable;

public class PostNotFound extends BlogException implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int statusCode        = Status.NOT_FOUND.getStatusCode();

    public PostNotFound() {
        super("Post not found in the system");
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
