package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(AutorizadaId.class)
public class Autorizada implements Serializable {

    @Id @GeneratedValue
    private Long id;
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
    private Date fechainicio;
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    // Requisito adicional RF15
    private String tipo;

    @Id
    private String AutorizadaId;

    @Id
    private String EmpresaId;

    @OneToOne
    Usuario user;

    public Autorizada(){

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

    public void setFechaNacimiento(Date fecha_nacimiento) {
        this.fechaNacimiento = fecha_nacimiento;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorizada that = (Autorizada) o;
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
                "\nfecha_nacimiento=" + fechaNacimiento +
                "\nestado=" + estado +
                "\nfechainicio=" + fechainicio +
                "\nfechafin=" + fechafin +
                "\ntipo=" + tipo +
                '}';
    }
}
