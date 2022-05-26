package rest;

import rest.classes.Name;
import rest.classes.SearchParameters;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("/fintech")
public class ServicioREST {

    SearchParameters searchParameters;

    @Context
    private UriInfo uriInfo;
    
    @Path("/api/healthcheck")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstado(){
      return  Response.ok().build();
    }

    @Path("/api/clients")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response infoCliente(@QueryParam("name") String name, @QueryParam("lastName") String lastName,
                                @QueryParam("startPeriod") String startPeriod, @QueryParam("endPeriod") String endPeriod){
        searchParameters = new SearchParameters();
        Name n = new Name(name, lastName);
        searchParameters.setName(n);
        searchParameters.setStartPeriod(startPeriod);
        searchParameters.setEndPeriod(endPeriod);
        URI uri = uriInfo.getBaseUriBuilder().path("fintech/api/clients/"+searchParameters.getJson()).build();
        return Response.created(uri).build();
    }
}

