package ma3s.fintech;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Pooled extends Fintech implements Serializable {
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
