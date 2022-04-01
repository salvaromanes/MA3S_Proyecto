package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Divisa implements Serializable {
    @Id
    private String abreviatura;
    @Column(nullable = false)
    private String nombre;
    private Character simbolo;
    @Column(nullable = false)
    private Double cambioEuro;

    // Faltan por completar campos
    /*@OneToMany(mappedBy = "divisaReceptor")
    private List<Transaccion> transaccionesReceptoras;
    @OneToMany(mappedBy = "divisaEmisor")
    private List<Transaccion> transaccionesEmisoras;
    */

    /*@OneToMany(mappedBy = "divisa")
    private List<Referencia> cuentasReferencia;
    */
    // Constructor vacio de la clase
    public Divisa(){

    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public Character getSimbolo() {
        return simbolo;
    }

    public Double getCambioEuro() {
        return cambioEuro;
    }

    /*public List<Transaccion> getTransaccionesReceptoras() {
        return transaccionesReceptoras;
    }

    public List<Transaccion> getTransaccionesEmisoras() {
        return transaccionesEmisoras;
    }*/

    /*public List<Referencia> getCuentasReferencia() {
        return cuentasReferencia;
    }*/

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSimbolo(Character simbolo) {
        this.simbolo = simbolo;
    }

    public void setCambioEuro(Double cambioEuro) {
        this.cambioEuro = cambioEuro;
    }

    /*public void setTransaccionesReceptoras(List<Transaccion> transaccionesReceptoras) {
        this.transaccionesReceptoras = transaccionesReceptoras;
    }

    public void setTransaccionesEmisoras(List<Transaccion> transaccionesEmisoras) {
        this.transaccionesEmisoras = transaccionesEmisoras;
    }*/

    /*public void setCuentasReferencia(List<Referencia> cuentasReferencia) {
        this.cuentasReferencia = cuentasReferencia;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Divisa divisa = (Divisa) o;
        return abreviatura.equals(divisa.abreviatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abreviatura);
    }

    @Override
    public String toString() {
        return "Divisa{\n" +
                "abreviatura=" + abreviatura +
                "\nnombre=" + nombre +
                "\nsimbolo=" + simbolo +
                "\ncambioEuro=" + cambioEuro +
                /*"\ntransaccionesReceptoras=" + transaccionesReceptoras +
                "\ntransaccionesEmisoras=" + transaccionesEmisoras +*/
                /*"\ncuentasReferencia=" + cuentasReferencia +*/
                '}';
    }
}
