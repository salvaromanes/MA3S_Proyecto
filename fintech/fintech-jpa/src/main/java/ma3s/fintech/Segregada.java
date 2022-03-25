package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Segregada extends Fintech implements Serializable {

    private double comision;

    public Segregada(){
        super();
    }

    @Column(name = "Comision", nullable = true)
    public double getComision(){
        return comision;
    }

    public void setComision(double c){
        comision=c;
    }

    @Override
    public String toString() {
        return super.toString() + "Segregada{\n" +
                "comision=" + comision +
                '}';
    }
}
