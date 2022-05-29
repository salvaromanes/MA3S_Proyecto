package rest;

import ma3s.fintech.*;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.GestionGetCuentasUnCliente;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import rest.classes.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("")
public class ServicioREST {
    @EJB
    private GestionGetClientes gestionGetClientes;

    @EJB
    private GestionGetCuentasUnCliente getCuentasUnicoCliente;

    @EJB
    private GestionGetCuentas getCuentas;

    @Context
    private UriInfo uriInfo;

    @Path("healthcheck")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Response getEstado() {
        String ok = "OK";
        return Response.ok(ok, MediaType.TEXT_PLAIN).build();
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
    @Path("/clients")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response infoCliente(PadreSearchParameters padreSearchParameters) {
        // Creamos el objeto que contiene lso datos
        Boolean comprobamos = true;
        PadreIndividual padreIndividual = new PadreIndividual();
        SearchParameters searchParameters = new SearchParameters();

        if (padreSearchParameters != null && padreSearchParameters.getSearchParameters() != null){
            searchParameters = padreSearchParameters.getSearchParameters();
        }else{
            comprobamos = false;
        }

        List<ma3s.fintech.Individual> coincidenciasIndi = new ArrayList<>();
        List<Individual> individuales = gestionGetClientes.getIndividuales();

        List<PAutorizada> pAutorizadas = gestionGetClientes.getPAutorizadas();
        List<PAutorizada> coincidenciasPAut = new ArrayList<>();

        // Hagamos la búsqueda por todos los clientes individuales que cumplan el searchParameters
        Boolean vamosBien = comprobamos;
        if(vamosBien) {
            for (ma3s.fintech.Individual indi : individuales) {

                if (searchParameters.getName() != null && vamosBien) {
                    if (searchParameters.getName().getFirstName() != null) {
                        if (!searchParameters.getName().getFirstName().equalsIgnoreCase(indi.getNombre()))
                            vamosBien = false;
                    }

                    if (searchParameters.getName().getLastName() != null && vamosBien) {
                        if (!searchParameters.getName().getLastName().equalsIgnoreCase(indi.getApellido()))
                            vamosBien = false;
                    }
                }

                if (searchParameters.getStartPeriod() != null && indi.getFechaAlta() != null && vamosBien) {
                    try {
                        Date inicioPeriodo = new SimpleDateFormat("yyyy-MM-dd").parse(searchParameters.getStartPeriod());
                        if (!inicioPeriodo.before(indi.getFechaAlta())) vamosBien = false;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (searchParameters.getEndPeriod() != null && indi.getFechaAlta() != null  && vamosBien) {
                    try {
                        Date finPeriodo = new SimpleDateFormat("yyyy-MM-dd").parse(searchParameters.getEndPeriod());
                        if (!finPeriodo.after(indi.getFechaAlta())) vamosBien = false;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (vamosBien) coincidenciasIndi.add(indi);
                vamosBien = true;
            }
        }else{
            coincidenciasIndi.addAll(individuales);
        }

        System.out.println(coincidenciasIndi);

        // Completamos la lista de padreIndividual con clientes
        for (ma3s.fintech.Individual indi : coincidenciasIndi){
            // Creo el objeto individual de la consulta
            rest.classes.Individual individualRest = new rest.classes.Individual();
            // Asingo la identificacion
            individualRest.setIndentificationNumber(indi.getIdentificacion());
            // Asigno la fecha de nacimiento
            individualRest.setDateOfBirth(indi.getFechaNacimiento());
            // Asigno el nombre, creando previamente un objeto con su primer apellido y nombre
            individualRest.setName(crearNombre(indi.getNombre(), indi.getApellido()));
            // Asigno la direccion creando antes un objeto
            individualRest.setAddress(establecerDireccion(indi));

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

        // Hacemos la busqueda por PAutorizadas
        vamosBien = comprobamos;
        if(vamosBien) {
            for (ma3s.fintech.PAutorizada paut : pAutorizadas) {

            if (searchParameters.getName() != null && vamosBien) {
                if (searchParameters.getName().getFirstName() != null) {
                    if (!searchParameters.getName().getFirstName().equalsIgnoreCase(paut.getNombre()))
                        vamosBien = false;
                }

                if (searchParameters.getName().getLastName() != null && vamosBien) {
                    if (!searchParameters.getName().getLastName().equalsIgnoreCase(paut.getApellidos()))
                        vamosBien = false;
                }
            }

            if (searchParameters.getStartPeriod() != null && paut.getFechaInicio()!= null && vamosBien) {
                try {
                    Date inicioPeriodo = new SimpleDateFormat("yyyy-MM-dd").parse(searchParameters.getStartPeriod());
                    if (!inicioPeriodo.before(paut.getFechaInicio())) vamosBien = false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (searchParameters.getEndPeriod() != null && paut.getFechaInicio() != null  && vamosBien) {
                try {
                    Date finPeriodo = new SimpleDateFormat("yyyy-MM-dd").parse(searchParameters.getEndPeriod());
                    if (!finPeriodo.after(paut.getFechaInicio())) vamosBien = false;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (vamosBien) coincidenciasPAut.add(paut);
            vamosBien = true;
        }
        }else{
            coincidenciasPAut.addAll(pAutorizadas);
        }

        System.out.println(coincidenciasPAut);

        // Completamos la lista de padreIndividual con clientes
        for (ma3s.fintech.PAutorizada paut : coincidenciasPAut){
            // Creo el objeto individual de la consulta
            rest.classes.Individual individualRest = new rest.classes.Individual();
            // Asingo la identificacion
            individualRest.setIndentificationNumber(paut.getIdentificacion());
            // Asigno la fecha de nacimiento
            individualRest.setDateOfBirth(paut.getFechaNacimiento());
            // Asigno el nombre, creando previamente un objeto con su primer apellido y nombre
            individualRest.setName(crearNombre(paut.getNombre(), paut.getApellidos()));
            // Asigno la direccion creando antes un objeto
            individualRest.setAddress(establecerDireccion(paut));
            // Establecemos el booleano de activo
            individualRest.setActiveCustomer(pasarABooleanoActiveCostumer(paut.getEstado()));

            List<Fintech> cuentasAuto = getCuentas.getAutorizacionesCliente(paut.getIdentificacion());
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
    @Path("/products")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response productos(PadreSearchParameters padreSearchParameters){
        PadreProducts padreProducts = new PadreProducts();
        Boolean comprobamos = true;

        List<ma3s.fintech.Fintech> cuentas = getCuentas.getFintech();
        List<ma3s.fintech.Fintech> cuentas2 = new ArrayList<>();
        List<ma3s.fintech.Fintech> cuentas3 = new ArrayList<>();

        if(padreSearchParameters == null) comprobamos = false;
        if(comprobamos && padreSearchParameters.getSearchParameters() == null) comprobamos = false;

        // Filtrado por Status
        if(comprobamos && padreSearchParameters.getSearchParameters().getStatus() != null){
            for(ma3s.fintech.Fintech cuenta : cuentas){
                if(cuenta.getEstado().equalsIgnoreCase(padreSearchParameters.getSearchParameters().getStatus())) cuentas2.add(cuenta);
            }
        }else{
            cuentas2.addAll(cuentas);
        }

        // Filtrado por IBAN
        if(comprobamos && padreSearchParameters.getSearchParameters().getProductNumber() != null){
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

        if(ma3s.fintech.PAutorizada.class.isInstance(o)){
            PAutorizada paut = (PAutorizada) o;
            direccion.setStreetNumber(paut.getDireccion());
        }

        return direccion;
    }

    private boolean pasarABooleanoActiveCostumer(String cadena){
        if(cadena == null) return false;
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
        accountHolder.setAddress(establecerDireccion(cliente));
        Long id = cliente.getId();
        Name name = new Name();
        try {
            Individual indi = gestionGetClientes.devolverIndividual(id);
            if (indi != null) {
                accountHolder.setName(crearNombre(indi.getNombre(), indi.getApellido()));
                accountHolder.setAddress(establecerDireccion(indi));
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
                accountHolder.setAddress(establecerDireccion(empre));
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
