package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Segregada extends Fintech implements Serializable {
    private Double comision;

    @OneToOne(mappedBy = "segregada")
    private Referencia referencia;

    public Segregada(){
        super();
    }

    public Double getComision(){
        return comision;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setComision(Double c){
        comision=c;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return  super.toString() + "Cuenta Segregada{\n" +
                "comision=" + comision +
                '}';
    }
}
