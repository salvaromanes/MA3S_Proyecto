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
    private String identificacion;
    private String tipo_cliente;
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fecha_alta;
    @Temporal(TemporalType.DATE)
    private Date fecha_baja;
    private String direccion;
    private String ciudad;
    private String codigopostal;
    private String pais;

    @OneToMany(mappedBy = "cliente")
    private List<Fintech> cuentas_fintech;

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

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public String getEstado() {
        return estado;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public List<Fintech> getCuentas_fintech() {
        return cuentas_fintech;
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

    public String getPais() {
        return pais;
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

    public void setCuentas_fintech(List<Fintech> cuentas_fintech) {
        this.cuentas_fintech = cuentas_fintech;
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
                "\ntipo_cliente=" + tipo_cliente +
                "\nestado=" + estado +
                "\nfecha_alta=" + fecha_alta +
                "\nfecha_baja=" + fecha_baja +
                "\ndireccion=" + direccion +
                "\nciudad=" + ciudad +
                "\ncodigopostal=" + codigopostal +
                "\npais=" + pais +
                "\ncuentas_fintech=" + cuentas_fintech +
                '}';
    }

}
