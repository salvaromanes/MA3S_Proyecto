package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Referencia extends Cuenta{
    private String nombrebanco;
    private String sucursal;
    private String pais;
    private double saldo;
    private Date fecha_apertura;
    private String estado;

    //Creacion de una referencia a una cueta, dado el IBAN.
    //Se indicará como fecha de creación la del día en que se haga la llamada al contructor y
    //el resto de datos se extraeran del IBAN de la cuenta.
    public Referencia(){
        super();
    }

    public Referencia(String i){
        super();
        nombrebanco=i.substring(4, 8);
        sucursal=i.substring(8, 12);
        pais=i.substring(0, 2);
        saldo=0;
        fecha_apertura=new java.util.Date();
        estado="abierta";
    }

    //Devuelve el codigo que hace referencia al nombre del banco
    @Column(name = "Nombre_Banco", nullable = false, length = 50)
    public String getNombrebanco() {
        return nombrebanco;
    }

    //Devuelve el codigo que hace referencia a la sucursal del banco
    @Column(name = "Sucursal", nullable = true, length = 50)
    public String getSucursal(){
        return sucursal;
    }

    //Devuelve el saldo de la cuenta
    @Column(name = "Saldo", nullable = false)
    public double getSaldo() {
        return saldo;
    }

    //Devuelve el estado de la cuenta
    @Column(name = "Estado", nullable = true, length = 40)
    public String getEstado() {
        return estado;
    }

    //Devuelve el pais donde esta creada la cuenta
    @Column(name = "Pais", nullable = true, length = 50)
    public String getPais() {
        return pais;
    }

    //Devuelve la fecha de creacion de la cuenta
    @Column(name = "Fecha_Apertura", nullable = true)
    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    //Actualiza el valor del nombre del banco
    public void setNombrebanco(String n) {
        nombrebanco=n;
    }

    //Actualiza el valor del saldo de la cuenta
    public void setSucursal(String s){
        sucursal=s;
    }

    //Actualiza el valor del pais de la cuenta
    public void setPais(String p){
        pais=p;
    }

    //Actualiza el valor del saldo de la cuenta
    public void setSaldo(double s){
        saldo=s;
    }

    //Actualiza el valor de la fecha de apertura de la cuenta
    public void setFecha_apertura(Date f){
        fecha_apertura=f;
    }

    //Actualiza el estado de la cuenta
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
    /*
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
    */
}
