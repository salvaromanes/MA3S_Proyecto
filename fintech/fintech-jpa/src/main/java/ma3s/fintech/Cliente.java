package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente implements Serializable {
    @Id
    private Long id;
    @Column(nullable = false)
    private String identificacion;
    @Column(nullable = false)
    private String tipoCliente;
    @Column(nullable = false)
    private String estado;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String codigopostal;
    @Column(nullable = false)
    private String pais;

    @OneToMany(mappedBy = "cliente")
    private List<Fintech> cuentasFintech;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    Usuario user;

    public Cliente(){

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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getEstado() {
        return estado;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public List<Fintech> getCuentasFintech() {
        return cuentasFintech;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setFechaAlta(Date fecha_alta) {
        this.fechaAlta = fecha_alta;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCuentasFintech(List<Fintech> cuentasFintech) {
        this.cuentasFintech = cuentasFintech;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{\n" +
                "id=" + id +
                "\nidentificacion=" + identificacion +
                "\ntipoCliente=" + tipoCliente +
                "\nestado=" + estado +
                "\nfechaAlta=" + fechaAlta +
                "\nfechaBaja=" + fechaBaja +
                "\ndireccion=" + direccion +
                "\nciudad=" + ciudad +
                "\ncodigopostal=" + codigopostal +
                "\npais=" + pais +
                "\ncuentasFintech=" + cuentasFintech +
                '}';
    }

}
