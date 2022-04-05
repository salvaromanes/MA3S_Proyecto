package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Individual extends Cliente implements Serializable {

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return super.toString() + "Individual{\n" +
                "nombre=" + nombre +
                "\napellido=" + apellido +
                "\nfechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
