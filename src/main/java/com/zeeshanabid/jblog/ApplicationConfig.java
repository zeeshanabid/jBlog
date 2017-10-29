package com.zeeshanabid.jblog;

import com.zeeshanabid.jblog.controller.PostController;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/blog")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        super(PostController.class);
        packages("com.zeeshanabid.jblog");
    }

}
