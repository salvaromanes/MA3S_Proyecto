package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(AutorizadaId.class)
public class Autorizacion implements Serializable {

    @Id
    @ManyToOne
    private PAutorizada autorizadaId;

    @Id
    @ManyToOne
    private Empresa empresaId;

    @Column(nullable = false)
    private String tipo;

    public Autorizacion(){

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PAutorizada getAutorizadaId() {
        return autorizadaId;
    }

    public void setAutorizadaId(PAutorizada autorizadaId) {
        this.autorizadaId = autorizadaId;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autorizacion that = (Autorizacion) o;
        return Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo);
    }

    @Override
    public String toString() {
        return "Autorizacion{\n" +
                "tipo=" + tipo +
                "\npAutorizadaId=" + autorizadaId +
                "\nempresaId=" + empresaId +
                '}';
    }
}
