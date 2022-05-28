package rest;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Fintech;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.GestionGetCuentasUnCliente;
import ma3s.fintech.ejb.GetClientes;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.ErrorInternoException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import rest.classes.*;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

/*
{
  "searchParameters" : {
    "name" : {
      "firstName" : "Manolo",
      "lastName" : "Garcia"
    },
    "startPeriod" : "2015-04-25",
    "endPeriod" : "2023-04-25"
  }
}

 */
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

        //System.out.println(individuales);
        //System.out.println(searchParameters.toString());

        // Recorremos la lista con todos los clientes y nos quedamos con aquellos que cumplan el searchParameters
        for (ma3s.fintech.Individual indi : individuales){
            if(indi.getNombre().equalsIgnoreCase(searchParameters.getName().getFirstName()) ||
                    indi.getApellido().equalsIgnoreCase(searchParameters.getName().getLastName())){
                try {
                    Date inicioPeriodo = new SimpleDateFormat("yyyy-MM-dd").parse(searchParameters.getStartPeriod());
                    Date finPeriodo = new SimpleDateFormat("yyyy-MM-dd").parse(searchParameters.getEndPeriod());
                    if (inicioPeriodo.before(indi.getFechaAlta()) && finPeriodo.after(indi.getFechaAlta())){
                        coincidencias.add(indi);
                    }
                }  catch (ParseException e) {
                    e.printStackTrace();
                }

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
            individualRest.setName(crearNombre(indi.getNombre(), indi.getApellido()));
            // Asigno la direccion creando antes un objeto
            individualRest.setDireccion(establecerDireccion(indi));

            // Establecemos el booleano de activo
            individualRest.setActiveCustomer(pasarABooleanoActiveCostumer(indi.getEstado()));

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


/*
{
  "searchParameters" : {
    "status" : "Activa",
    "productNumber" : "NL63ABNA6548268733",
  }
}
Si no se pasa algún parámetro la búsqueda se hace ignorando estos parámetros
 */
    @Path("/api/products")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response productos(PadreSearchParameters padreSearchParameters){
        PadreProducts padreProducts = new PadreProducts();

        List<ma3s.fintech.Fintech> cuentas = getCuentas.getFintech();
        List<ma3s.fintech.Fintech> cuentas2 = new ArrayList<>();
        List<ma3s.fintech.Fintech> cuentas3 = new ArrayList<>();

        // Filtrado por Status
        if(padreSearchParameters.getSearchParameters().getStatus() != null){
            for(ma3s.fintech.Fintech cuenta : cuentas){
                if(cuenta.getEstado().equalsIgnoreCase(padreSearchParameters.getSearchParameters().getStatus())) cuentas2.add(cuenta);
            }
        }else{
            cuentas2.addAll(cuentas);
        }

        // Filtrado por IBAN
        if(padreSearchParameters.getSearchParameters().getProductNumber() != null){
            for (ma3s.fintech.Fintech cuenta : cuentas2){
                if(cuenta.getIban().equalsIgnoreCase(padreSearchParameters.getSearchParameters().getProductNumber())) cuentas3.add(cuenta);
            }
        }else{
            cuentas3.addAll(cuentas2);
        }

        padreProducts.setProducts(new ArrayList<Products>());

        // Recorremos la lista de coincidencias y vamos rellenando los campos de padreProducts
        for(ma3s.fintech.Fintech cuenta : cuentas3){
            Products product = new Products();
            product.setProductNumber(cuenta.getIban());
            product.setStatus(cuenta.getEstado());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String fechaApertura = null;
            if(cuenta.getFechaApertura() != null) fechaApertura = dateFormat.format(cuenta.getFechaApertura());
            product.setStartDate(fechaApertura);

            String fechaCierre = null;
            if(cuenta.getFechaCierre() != null){
                fechaCierre = dateFormat.format(cuenta.getFechaCierre());
            }else{
                fechaCierre = "non-existent";
            }
            product.setEndDate(fechaCierre);

            product.setAccountHolder(crearAccountHolder(cuenta.getCliente()));
            padreProducts.getProducts().add(product);
        }

        return Response.ok(padreProducts, MediaType.APPLICATION_JSON).build();
    }

    private Direccion establecerDireccion(Object o){
        Direccion direccion = new Direccion();

        if(ma3s.fintech.Individual.class.isInstance(o)){
            Individual indi = (Individual) o;
            direccion.setCity(indi.getCiudad());
            direccion.setCountry(indi.getPais());
            direccion.setPostalCode(indi.getCodigopostal());
            direccion.setStreetNumber(indi.getDireccion());
        }

        if(ma3s.fintech.Empresa.class.isInstance(o)){
            Empresa empresa = (Empresa) o;
            direccion.setCity(empresa.getCiudad());
            direccion.setCountry(empresa.getPais());
            direccion.setPostalCode(empresa.getCodigopostal());
            direccion.setStreetNumber(empresa.getDireccion());
        }

        if(ma3s.fintech.Cliente.class.isInstance(o)){
            Cliente cliente = (Cliente) o;
            direccion.setCity(cliente.getCiudad());
            direccion.setCountry(cliente.getPais());
            direccion.setPostalCode(cliente.getCodigopostal());
            direccion.setStreetNumber(cliente.getDireccion());
        }

        return direccion;
    }

    private boolean pasarABooleanoActiveCostumer(String cadena){
        return cadena.equalsIgnoreCase("ACTIVO");
    }

    private Name crearNombre(String nombre, String apellidos){
        Name name = new Name();
        name.setFirstName(nombre);
        name.setLastName(apellidos);
        return name;
    }

    private AccountHolder crearAccountHolder(ma3s.fintech.Cliente cliente){
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setDireccion(establecerDireccion(cliente));
        Long id = cliente.getId();
        Name name = new Name();
        try {
            Individual indi = gestionGetClientes.devolverIndividual(id);
            if (indi != null) {
                accountHolder.setName(crearNombre(indi.getNombre(), indi.getApellido()));
                accountHolder.setDireccion(establecerDireccion(indi));
                accountHolder.setAccounttype("Fisica");
                accountHolder.setActiveCustomer(pasarABooleanoActiveCostumer(indi.getEstado()));
            }
        } catch (PersonaNoExisteException e) {
                e.printStackTrace();
        }
        try{
            Empresa empre = gestionGetClientes.devolverEmpresa(id);
            if(empre != null){
                name.setName(empre.getRazonSocial());
                accountHolder.setName(name);
                System.out.println(accountHolder.getName().getName());
                accountHolder.setDireccion(establecerDireccion(empre));
                accountHolder.setAccounttype("Empresa");
                accountHolder.setActiveCustomer(pasarABooleanoActiveCostumer(empre.getEstado()));
                System.out.println("He llegado al final de accountholder empresa");
            }
        } catch (EmpresaNoExistenteException e) {
            e.printStackTrace();
        }
        return accountHolder;
    }

}
