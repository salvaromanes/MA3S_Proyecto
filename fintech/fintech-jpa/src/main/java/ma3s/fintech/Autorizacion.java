package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Autorizacion implements Serializable {

    @Column(nullable = false)
    private String tipo;

    @Id
    @ManyToOne
    @JoinColumn(name = "p_autorizada")
    private Autorizada _autorizada;

    @Id
    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    public Autorizacion(){

    }

    public Autorizada getP_autorizada() {
        return _autorizada;
    }

    public void setP_autorizada(Autorizada _autorizada) {
        this._autorizada = _autorizada;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Autorizada get_autorizada() {
        return _autorizada;
    }

    public void set_autorizada(Autorizada _autorizada) {
        this._autorizada = _autorizada;
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
                "\np_autorizada=" + _autorizada +
                "\nempresa=" + empresa +
                '}';
    }

}
