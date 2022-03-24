package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Individual extends Cliente{


    private String nombre;
    private String apellido;
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;


    public Individual(){
        super();
    }

    public Individual(String id, String identificacion, String tipo_cliente, Date fecha_alta, Date fecha_baja, String direccion, String ciudad, String codigo_postal,
                      String pais, String nombre, String apellido, Date fecha_nacimiento) throws Exception {
        super(id, identificacion, tipo_cliente, fecha_alta, fecha_baja, direccion, ciudad, codigo_postal, pais);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
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
        return super.toString() + "Individual \n" +
                "nombre='" + nombre + "\n" +
                ", apellido='" + apellido + "\n" +
                ", fecha_nacimiento=" + fecha_nacimiento;
    }
}
