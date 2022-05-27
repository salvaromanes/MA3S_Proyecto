package rest.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/*
*   Clase para guardar los datos de una cuenta del cliente
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {

    // IBAN de la cuenta
    private String productNumber;

    // Estado de la cuenta
    private String status;

    // La relación entre el cliente y la cuenta "propietaria" o "autorizada"
    private String relationship;

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
