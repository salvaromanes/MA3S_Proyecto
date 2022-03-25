package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Autorizacion implements Serializable {

    private String tipo;

    @Id
    @ManyToOne
    @JoinColumn(name = "p_autorizada")
    private P_Autorizada p_autorizada;

    @Id
    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresa;


    public Autorizacion(){

    }

    public P_Autorizada getP_autorizada() {
        return p_autorizada;
    }

    public void setP_autorizada(P_Autorizada p_autorizada) {
        this.p_autorizada = p_autorizada;
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
        return "Autorizacion{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
