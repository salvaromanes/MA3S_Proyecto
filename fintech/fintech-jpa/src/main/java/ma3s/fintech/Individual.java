package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Individual extends Cliente implements Serializable {


    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;


    public Individual(){
       super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return super.toString() + "Individual{\n" +
                "nombre=" + nombre +
                "\napellido=" + apellido +
                "\nfecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
}
