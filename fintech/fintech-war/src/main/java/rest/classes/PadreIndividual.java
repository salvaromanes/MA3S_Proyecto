package rest.classes;

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
    public void rellenarCampos(SearchParameters searchParameters){



    }

}
