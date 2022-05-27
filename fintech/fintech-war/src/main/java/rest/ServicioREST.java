package rest;

import ma3s.fintech.Fintech;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.GestionGetCuentasUnCliente;
import ma3s.fintech.ejb.GetClientes;
import ma3s.fintech.ejb.excepciones.ErrorInternoException;
import rest.classes.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/fintech")
public class ServicioREST {
    @EJB
    private GestionGetClientes gestionGetClientes;

    @EJB
    private GestionGetCuentasUnCliente getCuentasUnicoCliente;

    @EJB
    private GestionGetCuentas getCuentas;

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
        // Creamos el objeto que contiene lso datos
        PadreIndividual padreIndividual = new PadreIndividual();
        SearchParameters searchParameters = padreSearchParameters.getSearchParameters();

        // Lista para los clientes que cumplan con los parámetros de búsqueda
        List<ma3s.fintech.Individual> coincidencias = new ArrayList<>();

        // Lista con todos los clientes individuales
        List<Individual> individuales = gestionGetClientes.getIndividuales();

        System.out.println(individuales);
        System.out.println(searchParameters.toString());

        // Recorremos la lista con todos los clientes y nos quedamos con aquellos que cumplan el searchParameters
        for (ma3s.fintech.Individual indi : individuales){
            if(indi.getNombre().equals(searchParameters.getName().getFirstName()) ||
                    indi.getApellido().equals(searchParameters.getName().getLastName())){
                coincidencias.add(indi);
            }
        }

        System.out.println(coincidencias);


        for (ma3s.fintech.Individual indi : coincidencias){
            // Creo el objeto individual de la consulta
            rest.classes.Individual individualRest = new rest.classes.Individual();
            // Asingo la identificacion
            individualRest.setIdentificacion(indi.getIdentificacion());
            // Asigno la fecha de nacimiento
            individualRest.setDateOfBirth(indi.getFechaNacimiento());
            // Asigno el nombre, creando previamente un objeto con su primer apellido y nombre
            Name name = new Name();
            name.setFirstName(indi.getNombre());
            name.setLastName(indi.getApellido());
            individualRest.setName(name);
            // Asigno la direccion creando antes un objeto
            Direccion direccion = new Direccion();
            direccion.setCity(indi.getCiudad());
            direccion.setCountry(indi.getPais());
            direccion.setPostalCode(indi.getCodigopostal());
            direccion.setStreetNumber(indi.getDireccion());
            individualRest.setDireccion(direccion);
            boolean estado = false;
            if(indi.getEstado().equals("ACTIVO")) estado = true;
            individualRest.setActiveCustomer(estado);

            System.out.println(individualRest);

            List<Fintech> cuentas = getCuentasUnicoCliente.getCuentas(indi.getIdentificacion());
            // Aqui estoy recorriendo las propietarias
            for(Fintech cuenta : cuentas){
                Products product = new Products();
                product.setProductNumber(cuenta.getIban());
                product.setStatus(cuenta.getEstado());
                product.setRelationship("propietaria");
                individualRest.getProducts().add(product);
            }

            List<Fintech> cuentasAuto = getCuentas.getAutorizacionesCliente(indi.getIdentificacion());
            // Cuentas donde tiene autorizacion
            for(Fintech cuenta : cuentasAuto){
                Products product = new Products();
                product.setRelationship("autorizada");
                product.setProductNumber(cuenta.getIban());
                product.setStatus(cuenta.getEstado());
                individualRest.getProducts().add(product);
            }

            padreIndividual.getIndividual().add(individualRest);
        }

        return Response.ok(padreIndividual, MediaType.APPLICATION_JSON).build();
    }

    @Path("/api/products")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response productos(PadreSearchParameters padreSearchParameters){
        PadreProducts padreProducts = new PadreProducts();

        return Response.ok(padreSearchParameters, MediaType.APPLICATION_JSON).build();
    }

}
