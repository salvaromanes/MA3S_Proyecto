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
    private String estado;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Boolean esAdmin;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "idPAutorizada")
    PAutorizada pAutorizada;

    @OneToOne(mappedBy = "user")
    @JoinColumn(name = "idCliente")
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public PAutorizada getAutorizada() {
        return pAutorizada;
    }

    public void setAutorizada(PAutorizada pAutorizada) {
        this.pAutorizada = pAutorizada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PAutorizada getpAutorizada() {
        return pAutorizada;
    }

    public void setpAutorizada(PAutorizada pAutorizada) {
        this.pAutorizada = pAutorizada;
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
                "\nestado='" + estado +
                '}';
    }
}
