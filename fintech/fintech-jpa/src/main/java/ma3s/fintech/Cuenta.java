package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cuenta {
    @Id
    private String iban;
    private String swift;

    /*
    //Creación de una cuenta a partir de un número de IBAN y un SWIFT dado por Ebury
    public Cuenta(String i, String s){
        iban=i;
        swift=s;
    }

    //Creación de una cuenta a partir de un número de IBAN
    public Cuenta(String i){
        iban=i;
    }
    */

    //Returnea el IBAN
    @Column(name = "IBAN", nullable = false, length = 32)
    public String getIban(){
        return iban;
    }

    //Returnea el SWIFT
    @Column(name = "SWIFT", nullable = true, length = 11)
    public String getSwift(){
        return swift;
    }

    //Adjudica un valor al IBAN
    public void setIban(String i){
        iban=i;
    }

    //Adjudica un valor al IBAN
    public void setSwift(String s){
        swift=s;
    }

    //Permite mostrar por pantalla los datos de la cuenta con el formato siguiente:
    /*
    IBAN: XXXXXXXXXXXXX
    SWIFT: XXXXXXXXXXXXX
    */
    /*
    @Override
    public String toString(){
        return "IBAN: "+getIban()+"\nSWIFT: "+getSwift();
    }
    */
}
