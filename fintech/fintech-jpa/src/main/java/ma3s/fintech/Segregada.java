package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Segregada extends Fintech{
    private double comision;

    //Creacion de una cuenta segregada, dados el IBAN y la comision aplicada
    public Segregada(){
        super();
        //comision=c;
    }

    //Devuelve el valor de la comision aplicada a la cuenta
    @Column(name = "Comision", nullable = true)
    public double getComision(){
        return comision;
    }

    //Actualiza el valor de la comision aplicada sobre la cuenta
    public void setComision(double c){
        comision=c;
    }

    //Permite mostrar por pantalla los datos de la cuenta con el formato siguiente:
    /*
    IBAN: XXXXXXXXXXXXX
    Fecha de apertura: dd/mm/yyyy
    Fecha de cierre: dd/mm/yyyy
    Clasificacion: XXXXXXX
    Comision: XXXX%
    */
    /*
    @Override
    public String toString(){
        return super.toString()+"\nComision: "+comision+"%";
    }
    */
}
