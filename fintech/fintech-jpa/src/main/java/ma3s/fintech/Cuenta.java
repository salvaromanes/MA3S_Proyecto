package ma3s.fintech;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta {
    @Id
    private String iban;
    private String swift;

    public Cuenta(){}

    public Cuenta(String i, String s){
        iban=i;
        swift=s;
    }

    public Cuenta(String i){ iban=i; }

    @Column(name = "IBAN", nullable = false, length = 32)
    public String getIban(){
        return iban;
    }

    @Column(name = "SWIFT", nullable = true, length = 11)
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
    public int hashCode() {
        return this.iban.toUpperCase().hashCode() ;
    }


    //Permite mostrar por pantalla los datos de la cuenta con el formato siguiente:
    /*
    IBAN: XXXXXXXXXXXXX
    SWIFT: XXXXXXXXXXXXX
    */
    @Override
    public String toString(){
        return "IBAN: "+getIban()+"\nSWIFT: "+getSwift();
    }
}
