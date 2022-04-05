package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Usuario implements Serializable {
    @Id
    private String usuario;
    @Column(nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false)
    private String direccion;
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;
    private String estado;
    @Column(nullable = false)
    private String rol;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Boolean esAdmin;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "Id_PAutorizada")
    Autorizada autorizada;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "Id_Cliente")
    Cliente cliente;

    public Usuario(){

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public Autorizada getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(Autorizada autorizada) {
        this.autorizada = autorizada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(usuario, usuario1.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario);
    }

    @Override
    public String toString() {
        return "Usuario{\n" +
                "usuario='" + usuario +
                "\ncontrasena='" + contrasena +
                "\nnombre='" + nombre +
                "\napellidos='" + apellidos +
                "\ndireccion='" + direccion +
                "\nfecha_nacimiento=" + fecha_nacimiento +
                "\nestado='" + estado +
                "\nrol='" + rol +
                '}';
    }
}
