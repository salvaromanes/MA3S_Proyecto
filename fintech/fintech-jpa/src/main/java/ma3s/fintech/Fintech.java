package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Fintech extends Cuenta implements Serializable {
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;
    @Temporal(TemporalType.DATE)
    private Date fecha_cierre;
    private String clasificacion;

    public Fintech(){
        super();
    }

    @Column(name = "Estado", nullable = false, length = 10)
    public String getEstado(){
        return estado;
    }

    @Column(name = "Fecha_Apertura", nullable = false)
    public Date getFecha_apertura(){
        return fecha_apertura;
    }

    @Column(name = "Fecha_Cierre", nullable = true)
    public Date getFecha_cierre(){
        return fecha_cierre;
    }

    @Column(name = "Clasificacion", nullable = true)
    public String getClasificacion(){
        return clasificacion;
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

    @Override
    public String toString() {
        return super.toString() + "Fintech{\n" +
                "estado=" + estado +
                "\nfecha_apertura=" + fecha_apertura +
                "\nfecha_cierre=" + fecha_cierre +
                "\nclasificacion=" + clasificacion +
                '}';
    }
}
