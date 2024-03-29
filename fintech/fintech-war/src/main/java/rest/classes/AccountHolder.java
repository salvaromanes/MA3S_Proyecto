package rest.classes;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountHolder {

    // Boolean si el cliente está activo o no
    private boolean activeCustomer;

    // Tipo de cuenta, Fisica o Empresa
    private String accounttype;

    // En el caso de ser una persona fisica
    private Name name;

    // En el caso de ser una empresa
    // private String name;

    // Direccion del cliente
    private Direccion address;

    public boolean getActiveCustomer() {
        return activeCustomer;
    }
    public void setActiveCustomer(boolean activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public String getAccounttype() {
        return accounttype;
    }
    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }

    public Direccion getAddress() {
        return address;
    }
    public void setAddress(Direccion address) {
        this.address = address;
    }
}
