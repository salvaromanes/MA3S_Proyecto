
package rest;


import ma3s.fintech.Cliente;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.AccesoAplicacion;
import ma3s.fintech.ejb.GeneracionInfHolanda;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.CampoVacioException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fintech")
public class ServicioREST {

    
    @Path("/api/healthcheck")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstado(){
        String str = "OK";
        return  Response.ok(str).build();
    }


    @Path("/api/clients")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response infoCliente(Cliente cliente){

        return null;

    }

}

