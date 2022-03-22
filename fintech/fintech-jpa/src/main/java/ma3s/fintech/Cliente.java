package ma3s.fintech;

import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Cliente{
    private String id;
    private String identificacion;
    private String tipo_cliente;
    private Date fecha_alta;
    private Date fecha_baja;
    private String direccion;
    private String ciudad;
    private String codigo_postal;
    private String pais;


    public Cliente(String id,String identificacion,String tipo_cliente, Date fecha_alta,Date fecha_baja,String direccion,String ciudad,String codigo_postal,String pais) {
        if( id == null || identificacion == null || tipo_cliente == null || direccion == null || ciudad == null
            || codigo_postal == null || pais == null){
            throw new RuntimeException("Parametros pasados erroneos");
        }
        this.id = id;
        this.identificacion = identificacion;
        this.tipo_cliente = tipo_cliente;
        this.fecha_alta = fecha_alta;
        this.fecha_baja = fecha_baja;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigo_postal = codigo_postal;
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public Date getFecha_baja() {
        return fecha_baja;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public String getPais() {
        return pais;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public void setFecha_baja(Date fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if(o instanceof Cliente){
            Cliente aux = (Cliente) o;
            res = this.id.equalsIgnoreCase(aux.getId());
        }
        return res;
    }



    @Override
    public int hashCode() {
        return this.id.toUpperCase().hashCode() ;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", tipo_cliente='" + tipo_cliente + '\'' +
                ", fecha_alta=" + fecha_alta +
                ", fecha_baja=" + fecha_baja +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
