package ma3s.fintech;

import javax.persistence.Entity;

@Entity
public class Pooled extends Fintech{
    public Pooled(){
        super();
    }

    //Permite mostrar por pantalla los datos de la cuenta con el formato siguiente:
    /*
    IBAN: XXXXXXXXXXXXX
    Fecha de apertura: dd/mm/yyyy
    Fecha de cierre: dd/mm/yyyy
    Clasificacion: XXXXXXX
    */
    @Override
    public String toString(){
        return super.toString();
    }
}
