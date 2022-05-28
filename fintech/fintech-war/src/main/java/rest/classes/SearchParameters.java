package rest.classes;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Date;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchParameters {

    //@XmlTransient
    //@JsonbTransient
    private Name name;

    //private SearchParameters searchParameters;

    @JsonbDateFormat("yyyy-mm-dd")
    private String startPeriod;
    @JsonbDateFormat("yyyy-mm-dd")
    private String endPeriod;

    private String status;

    private String productNumber;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductNumber() {
        return productNumber;
    }
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getStartPeriod() {
        return startPeriod;
    }
    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }
    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Nombre = " + name.getFirstName() + "; Apellidos = " + name.getLastName() + "; Fecha inicio = " + startPeriod
                + "; Fecha fin = " + endPeriod;
    }
}