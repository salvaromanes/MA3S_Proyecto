package ma3s.fintech;

import ma3s.fintech.excepciones.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class GeneracionInfHolanda implements GestionInfHolanda {

    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public String CuentasApi(Cuenta cuenta) throws CuentaNoExistenteException {
        Cuenta cuenta_Aux = em.find(Cuenta.class, cuenta);
        if(!cuenta_Aux.equals(cuenta)){
            throw new CuentaNoExistenteException("La cuenta : " + cuenta_Aux.getIban() +  " no se encuentra" );
        }
        return ;
    }

    @Override
    public String ClienteApi(Cliente cliente) throws ClienteNoExisteException {
         Cliente cliente1 = em.find(Cliente.class, cliente);
         if(!cliente1.equals(cliente)){
             throw  new ClienteNoExisteException("El cliente : " + cliente1.getId() + " no existe");
         }
        String aux = "{\n \"searchParametres\":{" +
                 "\n \"quesionType\": \"Customer,\"" +
                 "\"startPeriod\":"+ cliente.getFechaAlta() +",\n" +
                 "\"endPeriod\":"+ cliente.getFechaBaja() +",\n"+
            "\"name\"{:"+ cliente.getUser() +"},\n" +
                "\"address\"{:"+
                "\"Number\":"+ cliente.get +",\n" +
                cliente.getFechaAlta() +",\n"
                ;

         return  aux;

    }

    @Override
    public String PAutorApi(PAutorizada autorizada) throws NoEsPAutorizadaException {
        PAutorizada autorizada1 = em.find(PAutorizada.class,autorizada);
        if(!autorizada1.equals(autorizada)){
            throw new NoEsPAutorizadaException("La persona  "+  autorizada1.getId() + " no es autorizada" );
        }
    }





}
