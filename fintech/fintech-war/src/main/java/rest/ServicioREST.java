
package rest;


import ma3s.fintech.Cliente;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.AccesoAplicacion;
import ma3s.fintech.ejb.GeneracionInfHolanda;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import rest.classes.SearchParameters;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/fintech")
public class ServicioREST {

    SearchParameters searchParameters;

    
    @Path("/api/healthcheck")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstado(){
      return  Response.ok().build();
    }


    @Path("/api/clients")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response infoCliente(Cliente cliente){

        return Response.ok(searchParameters.toString()).build();
    }

}

