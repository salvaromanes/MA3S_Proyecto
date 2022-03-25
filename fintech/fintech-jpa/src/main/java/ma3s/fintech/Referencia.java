package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Referencia extends Cuenta{
    private String nombrebanco;
    private String sucursal;
    private String pais;
    private double saldo;
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;
    private String estado;

    public Referencia(){
        super();
    }

    @Column(name = "Nombre_Banco", nullable = false, length = 50)
    public String getNombrebanco() {
        return nombrebanco;
    }

    @Column(name = "Sucursal", nullable = true, length = 50)
    public String getSucursal(){
        return sucursal;
    }

    @Column(name = "Saldo", nullable = false)
    public double getSaldo() {
        return saldo;
    }

    @Column(name = "Estado", nullable = true, length = 40)
    public String getEstado() {
        return estado;
    }

    @Column(name = "Pais", nullable = true, length = 50)
    public String getPais() {
        return pais;
    }

    @Column(name = "Fecha_Apertura", nullable = true)
    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setNombrebanco(String n) {
        nombrebanco=n;
    }

    public void setSucursal(String s){
        sucursal=s;
    }

    public void setPais(String p){
        pais=p;
    }

    public void setSaldo(double s){
        saldo=s;
    }

    public void setFecha_apertura(Date f){
        fecha_apertura=f;
    }

    public void setEstado(String e){
        estado=e;
    }


    //Permite mostrar por pantalla los datos de la cuenta con el formato siguiente:
    /*
    IBAN: XXXXXXXXXXXXX
    Nombre del banco: XXXXXXXXXX
    Sucursal: XXXXXXX
    Pais: XX
    Saldo: XXXXXXXX
    Fecha de apertura: dd/mm/yyyy
    Estado: XXXXXXX
    */
    @Override
    public String toString(){
        String fa, fc;
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
        fa=formato.format(getFecha_apertura());

        return "IBAN: "+super.getIban()+"\nNombre del banco: "+getNombrebanco()+
                "\nSucursal: "+getSucursal()+"\nPais: "+getPais()+
                "\nSaldo: "+getSaldo()+"\nFecha de apertura: "+fa+
                "\nEstado de la cuenta: "+getEstado();
    }
}
