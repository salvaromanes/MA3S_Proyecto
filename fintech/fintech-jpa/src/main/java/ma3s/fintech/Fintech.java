package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Fintech extends Cuenta{
    private String estado;
    private Date fecha_apertura;
    private Date fecha_cierre;
    private String clasificacion;

    //Creacion de una cuenta fintech, dados el IBAN y el tipo de cuenta, otorgando valores a los atributos de esta.
    //Se indicará como fecha de creación la del día en que se haga la llamada al contructor y
    //como cierre el valor nulo ya que es desconocido

    public Fintech(){
        super();
        /*
        fecha_apertura=new java.util.Date();
        fecha_cierre=null;
        clasificacion=c;
        */
    }

    //Devuelve el estado de la cuenta en concreto
    @Column(name = "Estado", nullable = false, length = 10)
    public String getEstado(){
        return estado;
    }

    //Devuelve la fecha de creacion de la cuenta en concreto
    @Column(name = "Fecha_Apertura", nullable = false)
    public Date getFecha_apertura(){
        return fecha_apertura;
    }

    //Devuelve la fecha de cierre de la cuenta en concreto
    @Column(name = "Fecha_Cierre", nullable = true)
    public Date getFecha_cierre(){
        return fecha_cierre;
    }

    //Devuelve el tipo de cuenta
    @Column(name = "Clasificacion", nullable = true)
    public String getClasificacion(){
        return clasificacion;
    }

    //Indica el estado de la cuenta
    public void setEstado(String e){
        estado=e;
    }

    //Indica la fecha en la que se creo la cuenta
    public void setFecha_apertura(Date f){
        fecha_apertura=f;
    }

    //Indica la fecha en la que se cerro la cuenta
    public void setFecha_cierre(Date f){
        fecha_cierre=f;
    }

    //Indica la fecha en la que se cerro la cuenta
    public void setClasificacion(String c){
        clasificacion=c;
    }

    //Permite mostrar por pantalla los datos de la cuenta con el formato siguiente:
    /*
    IBAN: XXXXXXXXXXXXX
    Fecha de apertura: dd/mm/yyyy
    Fecha de cierre: dd/mm/yyyy
    Clasificacion: XXXXXXX
    */
    /*
    @Override
    public String toString(){
        String fa, fc;
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        fa=formato.format(getFecha_apertura());
        fc=formato.format(getFecha_cierre());

        return "IBAN: "+super.getIban()+"\nFecha de apertura: "+
                fa+"\nFecha de cierre: "+fc+"\nClasificacion: "+getClasificacion();
    }
    */
}
