package com.mobile.api;

import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/Device")
public interface Device {

    @GET
    @Path("/register/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Result<List<A>> register(@HeaderParam("User-Agent") String agent, @PathParam("id") String id, @CookieParam("sessionid") String sessionid, @QueryParam("zip") String zip);

}
