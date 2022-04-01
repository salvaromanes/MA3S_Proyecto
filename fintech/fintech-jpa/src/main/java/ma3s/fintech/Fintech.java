package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Fintech extends Cuenta implements Serializable {
    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;

    @Temporal(TemporalType.DATE)
    private Date fecha_cierre;
    private String clasificacion;

    @ManyToOne
    private Cliente cliente;

    public Fintech(){
        super();
    }

    public String getEstado(){
        return estado;
    }

    public Date getFecha_apertura(){
        return fecha_apertura;
    }

    public Date getFecha_cierre(){
        return fecha_cierre;
    }

    public String getClasificacion(){
        return clasificacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setEstado(String e){
        estado=e;
    }

    public void setFecha_apertura(Date f){
        fecha_apertura=f;
    }

    public void setFecha_cierre(Date f){
        fecha_cierre=f;
    }

    public void setClasificacion(String c){
        clasificacion=c;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return  super.toString() + "Cuenta Fintech{\n" +
                "estado=" + estado +
                "\nfecha_apertura=" + fecha_apertura +
                "\nfecha_cierre=" + fecha_cierre +
                "\nclasificacion=" + clasificacion +
                "\ncliente=" + cliente +
                '}';
    }
}
