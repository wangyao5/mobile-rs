package com.mobile.api;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/Device")
public interface Device {

    @POST
    @Path("/register/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Result<List<TestData>> register(@HeaderParam("User-Agent") String agent, @PathParam("id") String id, @HeaderParam("Cookie") String sessionid, @QueryParam("zip") String zip, @FormParam("form") String form);

}
