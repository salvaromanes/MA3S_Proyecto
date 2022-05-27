package rest.classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
    private Direccion direccion;

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

    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
