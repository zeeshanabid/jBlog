package com.zeeshanabid.jblog.exceptions;

public class PostNotFound extends Exception {
    public PostNotFound() {
        super("Post not found in the system");
    }
}
