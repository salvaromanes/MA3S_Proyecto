package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class PAutorizada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(unique=true, nullable = false)
    private String identificacion;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private String direccion;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private String estado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @OneToMany(mappedBy = "autorizadaId")
    private List<Autorizacion> autorizaciones;

    @OneToOne(mappedBy = "pAutorizada", cascade = CascadeType.ALL)
    Usuario user;

    public PAutorizada(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Autorizacion> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<Autorizacion> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PAutorizada that = (PAutorizada) o;
        return id.equals(that.id) && identificacion.equals(that.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificacion);
    }

    @Override
    public String toString() {
        return "P_Autorizada{\n" +
                "id=" + id +
                "\nidentificacion=" + identificacion +
                "\nnombre=" + nombre +
                "\napellidos=" + apellidos +
                "\ndireccion=" + direccion +
                "\nfechaNacimiento=" + fechaNacimiento +
                "\nestado=" + estado +
                "\nfechaInicio=" + fechaInicio +
                "\nfechaFin=" + fechaFin +
                '}';
    }
}
