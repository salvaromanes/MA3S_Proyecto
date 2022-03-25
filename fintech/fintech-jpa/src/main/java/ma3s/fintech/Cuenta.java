package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;

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
    public String toString() {
        return "Cuenta{\n" +
                "iban=" + iban +
                "\nswift=" + swift +
                '}';
    }

    @Override
    public int hashCode() {
        return this.iban.toUpperCase().hashCode() ;
    }


}
