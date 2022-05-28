package rest.classes;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


/*
*   Clase para guardar los datos de una cuenta del cliente
*/
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Products {

    // IBAN de la cuenta
    private String productNumber;

    // Estado de la cuenta
    private String status;

    // La relación entre el cliente y la cuenta "propietaria" o "autorizada"
    private String relationship;

    // Fecha inicio de la cuenta
    private Date startDate;

    // Fecha fin de la cuenta
    private Date endDate;

    // Propietario de la cuenta
    private AccountHolder accountHolder;

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductNumber() {
        return productNumber;
    }
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
