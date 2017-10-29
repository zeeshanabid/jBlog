package com.zeeshanabid.jblog.exceptions.handlers;

import com.zeeshanabid.jblog.exceptions.BlogException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class BlogExceptionHandler implements ExceptionMapper<BlogException>
{
    @Override
    public Response toResponse(BlogException exception)
    {
        Map<String, String> m = new HashMap<>();
        m.put("reason", exception.getMessage());
        return Response.status(exception.getStatusCode()).entity(m).build();
    }
}
