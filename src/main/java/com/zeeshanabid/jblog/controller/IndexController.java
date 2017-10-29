package com.zeeshanabid.jblog.controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/index")
public class IndexController {
    @GET
    @Path("/")
    public String index() {
       return "index.html";
    }
}
