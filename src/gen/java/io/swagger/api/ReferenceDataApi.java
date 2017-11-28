package io.swagger.api;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.ext.multipart.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.OAuth2Definition;
import io.swagger.jaxrs.PATCH;

@Path("/")
@Api(value = "/", description = "")
public interface ReferenceDataApi  {

    @GET
    @Path("/references/aircraft/{aircraftCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", tags={  })
    public String aircraft(@PathParam("aircraftCode") String aircraftCode, @HeaderParam("Accept") String accept, @QueryParam("limit")String limit, @QueryParam("offset")String offset);

    @GET
    @Path("/references/airlines/{airlineCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", tags={  })
    public String airlines(@PathParam("airlineCode") String airlineCode, @HeaderParam("Accept") String accept, @QueryParam("limit")String limit, @QueryParam("offset")String offset);

    @GET
    @Path("/references/airports/{airportCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", tags={  })
    public String airports(@PathParam("airportCode") String airportCode, @HeaderParam("Accept") String accept, @QueryParam("lang")String lang, @QueryParam("limit")String limit, @QueryParam("offset")String offset, @QueryParam("LHoperated")Boolean lhoperated);

    @GET
    @Path("/references/cities/{cityCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", tags={  })
//    @OAuth2Definition({ key = "oAuth2AccessCode" })
    public String cities(@PathParam("cityCode") String cityCode, @HeaderParam("Accept") String accept, @QueryParam("lang")String lang, @QueryParam("limit")String limit, @QueryParam("offset")String offset);

    @GET
    @Path("/references/countries/{countryCode}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", tags={  })
    public String countries(@PathParam("countryCode") String countryCode, @HeaderParam("Accept") String accept, @QueryParam("lang")String lang, @QueryParam("limit")String limit, @QueryParam("offset")String offset);

    @GET
    @Path("/references/airports/nearest/{latitude},{longitude}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", tags={  })
    public String nearestAirports(@PathParam("latitude") Integer latitude, @PathParam("longitude") Integer longitude, @HeaderParam("Accept") String accept, @QueryParam("lang")String lang);
}

