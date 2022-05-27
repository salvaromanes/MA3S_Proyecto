package rest.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PadreSearchParameters {

    // Objeto SearchParameters donde están guardado los parámetros de la búsqueda que hemos recibido con el método POST
    private SearchParameters searchParameters;

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }
}
