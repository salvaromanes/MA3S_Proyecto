package rest.classes;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
*   Clase para guardar los campos de individual, donde tenemos una lista con las cuentas del cliente y
*   datos relativos a él mismo.
*   ES POSIBLE QUE HAGA FALTA UN METODO PARA RELLENAR LA LISTA DE PRODUCTOS HACIENDO USO DE LA IDENTIFICACION YA ALMACENADA
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Individual {
//  Lista de objetos products, es decir lista con todas las cuentas a las que el cliente tiene acceso
    List<Products> products = new ArrayList<>();

//  Booleano para ver si el cliente está activo o no, si lo está se pone a true y sino a false
//  ¡OJO! en nuestro JPA está guardado como un String
    private Boolean activeCustomer;

//  Identificacion del cliente
    private String indentificationNumber;

//  Fecha de cumpleaños
    private Date dateOfBirth;

//  Nombre completo del cliente, ya que en la búsqueda puede que solo tengamos un parámetro
    private Name name;

//  Dirección del cliente
    private Direccion address;

    public List<Products> getProducts() {
        return products;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Boolean getActiveCustomer() {
        return activeCustomer;
    }
    public void setActiveCustomer(Boolean activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public String getIndentificationNumber() {
        return indentificationNumber;
    }
    public void setIndentificationNumber(String indentificationNumber) {
        this.indentificationNumber = indentificationNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

//    public void completarCampos(){
//        List<Fintech> cuentas = getCuentasUnicoCliente.getCuentas(identificacion);
//        // Aqui estoy recorriendo las propietarias
//        for(Fintech cuenta : cuentas){
//            Products product = new Products();
//            product.setProductNumber(cuenta.getIban());
//            product.setStatus(cuenta.getEstado());
//            product.setRelationship("propietaria");
//            products.add(product);
//        }
//
//        List<Fintech> cuentasAuto = getCuentas.getAutorizacionesCliente(identificacion);
//        // Cuentas donde tiene autorizacion
//        for(Fintech cuenta : cuentasAuto){
//            Products product = new Products();
//            product.setRelationship("autorizada");
//            product.setProductNumber(cuenta.getIban());
//            product.setStatus(cuenta.getEstado());
//        }


}


