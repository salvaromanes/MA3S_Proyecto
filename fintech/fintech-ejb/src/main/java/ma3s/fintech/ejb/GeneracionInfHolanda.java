package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GeneracionInfHolanda implements GestionInfHolanda {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public String CuentasApi(Segregada cuenta) throws CuentaNoExistenteException {
        Segregada cuenta_Aux = em.find(Segregada.class, cuenta.getIban());

        if(cuenta_Aux == null){
            throw new CuentaNoExistenteException();
        }

        Referencia referencia = em.find(Referencia.class, cuenta.getIban());

        if(referencia == null){
            throw new CuentaNoExistenteException();
        }

        String aux = "{\n \"searchParametres\":{" +
                "\n \"quesionType\": \"Product,\"" +
                "\"status\":"+ referencia.getEstado() +",\n" +
                "\"productNumber\":"+cuenta.getIban() +",\n" + "}}"
                ;

        return  aux;

    }

    @Override
    public String ClienteApi(Cliente cliente) throws ClienteNoExisteException {
         Cliente cliente1 = em.find(Cliente.class, cliente.getId());

         if(cliente1 == null){
             throw new ClienteNoExisteException();
         }

         if(!cliente1.equals(cliente)){
             throw  new ClienteNoExisteException("El cliente : " + cliente1.getId() + " no existe");
         }

         String aux = "{\n \"searchParametres\":{" +
                 "\n \"quesionType\": \"Customer,\"" +
                 "\"startPeriod\":"+ cliente.getFechaAlta() +",\n" +
                 "\"endPeriod\":"+ cliente.getFechaBaja() +",\n"+
            "\"name\"{:"+ cliente.getUser() +"},\n" +
                "\"address\"{:"+
                "\"Number\":"+ cliente.getDireccion() +",\n" +
                "\"postalCode\":"+ cliente.getCodigopostal() +",\n" +
                "\"Number\":"+ cliente.getPais() +",\n" + "}}"
                ;

         return  aux;

    }

    @Override
    public String PAutorApi(PAutorizada autorizada) throws NoEsPAutorizadaException {
        PAutorizada autorizada1 = em.find(PAutorizada.class, autorizada.getId());

        if(autorizada1 == null){
            throw new NoEsPAutorizadaException();
        }

        if(!autorizada1.getId().equals(autorizada.getId())){
            throw new NoEsPAutorizadaException("La persona  "+  autorizada1.getId() + " no es autorizada" );
        }

        String aux = "{\n \"searchParametres\":{" +
                "\n \"quesionType\": \"Customer,\"" +
                "\"startPeriod\":"+ autorizada.getFechaInicio() +",\n" +
                "\"endPeriod\":"+ autorizada.getFechaFin() +",\n"+
                "\"name\"{:"+ autorizada.getUser() +"},\n" +
                "\"address\"{:"+
                "\"Number\":"+ autorizada.getDireccion() +",\n"
                ;

        return  aux;
    }
}
