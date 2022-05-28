package rest.classes;



import com.fasterxml.jackson.annotation.JsonInclude;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.excepciones.ErrorInternoException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


/*
*   Padre de la clase individual, aquí tendremos una lista con los clientes y un método que es el que rellenerá los datos de esta lista
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PadreIndividual {

    // Lista con el cliente Individual
    List<Individual> individual = new ArrayList<>();

    public List<Individual> getIndividual() {
        return individual;
    }
    public void setIndividual(List<Individual> individual) {
        this.individual = individual;
    }

    // Metodo para rellenar la lista de individuales con los datos que se piden en la consulta post
//    public void rellenarCampos(SearchParameters searchParameters) throws ErrorInternoException {
//        // Lista para los clientes que cumplan con los parámetros de búsqueda
//        List<ma3s.fintech.Individual> coincidencias = new ArrayList<>();
//
//        // Lista con todos los clientes individuales
//        List<ma3s.fintech.Individual> individuales = getClientes.getIndividuales();
//
//        if(individuales == null) throw new ErrorInternoException("No hay clientes individuales");
//
//        for (ma3s.fintech.Individual indi : individuales){
//            if(indi.getNombre().equals(searchParameters.getName().getFirstName()) ||
//                    indi.getApellido().equals(searchParameters.getName().getLastName())){
//                coincidencias.add(indi);
//            }
//        }
//
//        for (ma3s.fintech.Individual indi : coincidencias){
//            // Creo el objeto individual de la consulta
//            Individual individualRest = new Individual();
//            // Asingo la identificacion
//            individualRest.setIdentificacion(indi.getIdentificacion());
//            // Asigno la fecha de nacimiento
//            individualRest.setDateOfBirth(indi.getFechaNacimiento());
//            // Asigno el nombre, creando previamente un objeto con su primer apellido y nombre
//            Name name = new Name();
//            name.setFirstName(indi.getNombre());
//            name.setLastName(indi.getApellido());
//            individualRest.setName(name);
//            // Asigno la direccion creando antes un objeto
//            Direccion direccion = new Direccion();
//            direccion.setCity(indi.getCiudad());
//            direccion.setCountry(indi.getPais());
//            direccion.setPostalCode(indi.getCodigopostal());
//            direccion.setStreetNumber(indi.getDireccion());
//            individualRest.setDireccion(direccion);
//
//            individualRest.completarCampos();
//
//            individual.add(individualRest);
//        }
//
//    }

}
