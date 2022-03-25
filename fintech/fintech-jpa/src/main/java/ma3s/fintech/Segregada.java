package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Segregada extends Fintech implements Serializable {

    private double comision;

    @OneToOne(mappedBy = "segregada")
    private Referencia referencia;

    public Segregada(){
        super();
    }

    public double getComision(){
        return comision;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setComision(double c){
        comision=c;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return super.toString() + "Segregada{\n" +
                "comision=" + comision +
                '}';
    }
}
