package com.zeeshanabid.jblog.exceptions;

import java.io.Serializable;

public abstract class BlogException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public BlogException(String message) {
        super(message);
    }

    public abstract int getStatusCode();
}
