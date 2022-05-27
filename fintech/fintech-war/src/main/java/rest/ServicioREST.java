package rest;

import rest.classes.Name;
import rest.classes.PadreIndividual;
import rest.classes.PadreSearchParameters;
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
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEstado() {
        String ok = "ok a todos";
        return Response.ok(ok, MediaType.APPLICATION_JSON).build();
    }

    @Path("/api/clients")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response infoCliente(PadreSearchParameters padreSearchParameters) {

        PadreIndividual padreIndividual = new PadreIndividual();
        padreIndividual.rellenarCampos(padreSearchParameters.getSearchParameters());

        return Response.ok(padreIndividual, MediaType.APPLICATION_JSON).build();
    }
}
