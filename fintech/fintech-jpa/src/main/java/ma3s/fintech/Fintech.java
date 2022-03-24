package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Fintech extends Cuenta{
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;
    @Temporal(TemporalType.DATE)
    private Date fecha_cierre;
    private String clasificacion;

    public Fintech(){
        super();
    }

    public Fintech(String c){
        super();
        fecha_apertura=new java.util.Date();
        fecha_cierre=null;
        clasificacion=c;
    }

    @Column(name = "Estado", nullable = false, length = 10)
    public String getEstado(){
        return estado;
    }

    @Column(name = "Fecha_Apertura", nullable = false)
    public Date getFecha_apertura(){
        return fecha_apertura;
    }

    @Column(name = "Fecha_Cierre", nullable = true)
    public Date getFecha_cierre(){
        return fecha_cierre;
    }

    @Column(name = "Clasificacion", nullable = true)
    public String getClasificacion(){
        return clasificacion;
    }

    public void setEstado(String e){
        estado=e;
    }

    public void setFecha_apertura(Date f){
        fecha_apertura=f;
    }

    public void setFecha_cierre(Date f){
        fecha_cierre=f;
    }

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
    @Override
    public String toString(){
        String fa, fc;
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        fa=formato.format(getFecha_apertura());
        fc=formato.format(getFecha_cierre());

        return "IBAN: "+super.getIban()+"\nFecha de apertura: "+
                fa+"\nFecha de cierre: "+fc+"\nClasificacion: "+getClasificacion();
    }
}
