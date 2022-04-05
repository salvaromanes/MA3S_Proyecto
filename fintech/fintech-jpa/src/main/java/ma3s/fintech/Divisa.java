package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
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
                '}';
    }
}
