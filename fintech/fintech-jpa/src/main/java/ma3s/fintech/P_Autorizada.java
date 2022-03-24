package ma3s.fintech;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;


public class P_Autorizada {
    private String id;
    private String identificacion;
    private String nombre;
    private String apellidos;
    private String direccion;
    private Date fecha_nacimiento;
    private String estado;
    private Date fechainicio;
    private Date fechafin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        P_Autorizada that = (P_Autorizada) o;
        return id.equals(that.id) && identificacion.equals(that.identificacion);
    }

    @Override
    public int hashCode() {
        return this.id.toUpperCase().hashCode()+ this.identificacion.toUpperCase().hashCode();
    }

    @Override
    public String toString() {
        return "P_Autorizada{" +
                "id='" + id + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", estado='" + estado + '\'' +
                ", fechainicio=" + fechainicio +
                ", fechafin=" + fechafin +
                '}';
    }
}
