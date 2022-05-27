package rest.classes;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Date;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@JsonInclude(Include.NON_NULL)
public class SearchParameters {

    //@XmlTransient
    //@JsonbTransient
    private Name name;

    //private SearchParameters searchParameters;

    @JsonbDateFormat("yyyy-MM-dd")
    private Date startPeriod;
    @JsonbDateFormat("yyyy-MM-dd")
    private Date endPeriod;

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

    public Date getStartPeriod() {
        return startPeriod;
    }
    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }
    public void setEndPeriod(Date endPeriod) {
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
        return "Nombre = " + name.getFirstName() + "; Apellidos = " + name.getLastName() + "; Fecha inicio = " + startPeriod.toString()
                + "; Fecha fin = " + endPeriod.toString();
    }
}