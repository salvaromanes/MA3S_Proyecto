package rest.classes;

import javax.print.attribute.standard.MediaSize;
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
public class Individual {

//  Lista de objetos products, es decir lista con todas las cuentas a las que el cliente tiene acceso
    List<Products> products = new ArrayList<>();

//  Booleano para ver si el cliente está activo o no, si lo está se pone a true y sino a false
//  ¡OJO! en nuestro JPA está guardado como un String
    private Boolean activeCustomer;

//  Identificacion del cliente
    private String identificacion;

//  Fecha de cumpleaños
    private Date dateOfBirth;

//  Nombre completo del cliente, ya que en la búsqueda puede que solo tengamos un parámetro
    private Name name;

//  Dirección del cliente
    private Direccion direccion;

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

    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
