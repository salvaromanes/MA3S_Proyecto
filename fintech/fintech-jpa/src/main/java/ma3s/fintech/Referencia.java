package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Referencia extends Cuenta implements Serializable {
    private String nombrebanco;
    private String sucursal;
    private String pais;
    private double saldo;
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;
    private String estado;

    @Override
    public String toString() {
        return super.toString() + "Referencia{\n" +
                "nombrebanco=" + nombrebanco +
                "\nsucursal=" + sucursal +
                "\npais=" + pais +
                "\nsaldo=" + saldo +
                "\nfecha_apertura=" + fecha_apertura +
                "\nestado=" + estado +
                "\ndivisa=" + divisa +
                '}';
    }

    @ManyToOne
    private Divisa divisa;

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

    public Divisa getDivisa() {
        return divisa;
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

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

}
