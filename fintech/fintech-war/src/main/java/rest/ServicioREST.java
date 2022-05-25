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

    @EJB
    private AccesoAplicacion accesoAplicacion;
    private GeneracionInfHolanda generacionInfHolanda;


    @HeaderParam("User-auth")
    private String datos;


    @Path("/api/healthcheck")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEstado(){
        Usuario usuario = getUsuario();
        if(usuario == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try{
            usuario = accesoAplicacion.refrescarUsuario(usuario);
            return Response.ok(usuario).build();
        } catch (CampoVacioException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch (AccesoException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }


    @Path("/api/clients")
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response infoCliente(Cliente cliente){
        Usuario usuario = getUsuario();
        if(usuario == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return null;

    }










    private Usuario getUsuario(){
        if(datos == null){
            return null;
        }

        String[] partes = datos.split(":");
        if(partes.length != 3){
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setUser(partes[0]);
        usuario.setContrasena(partes[1]);
        usuario.setEsAdmin(Boolean.valueOf((partes[2])));

        return usuario;
    }


}
