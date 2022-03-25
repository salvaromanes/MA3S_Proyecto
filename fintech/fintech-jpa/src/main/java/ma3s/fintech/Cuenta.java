package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta implements Serializable {
    @Id
    private String iban;
    private String swift;

    public Cuenta(){}

    public String getIban(){
        return iban;
    }

    public String getSwift(){
        return swift;
    }

    public void setIban(String i){ iban=i; }

    public void setSwift(String s){ swift=s; }

    @OneToMany(mappedBy = "cuentaDestino")
    private List<Transaccion> transaccionesDestino;

    @OneToMany(mappedBy = "cuentaOrigen")
    private List<Transaccion> transaccionesOrigen;

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if(o instanceof Cuenta){
            Cuenta aux = (Cuenta) o;
            res = this.iban.equalsIgnoreCase(aux.getIban());
        }
        return res;
    }

    @Override
    public int hashCode() {
        return this.iban.toUpperCase().hashCode() ;
    }

    @Override
    public String toString() {
        return "Cuenta{\n" +
                "iban=" + iban +
                "\nswift=" + swift +
                '}';
    }
}
