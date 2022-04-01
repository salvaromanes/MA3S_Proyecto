package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Referencia extends Cuenta implements Serializable {
    @Column(nullable = false)
    private String nombrebanco;

    private String sucursal;
    private String pais;

    @Column(nullable = false)
    private double saldo;
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;
    private String estado;

    @OneToOne
    private Segregada segregada;

    @ManyToOne
    private Divisa divisa;

    public Referencia(){
        super();
    }


    public String getNombrebanco() {
        return nombrebanco;
    }

    public String getSucursal(){
        return sucursal;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public Segregada getSegregada() {
        return segregada;
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

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    @Override
    public String toString() {
        return  super.toString() + "Referencia{\n" +
                "nombrebanco=" + nombrebanco +
                "\nsucursal=" + sucursal +
                "\npais=" + pais +
                "\nsaldo=" + saldo +
                "\nfecha_apertura=" + fecha_apertura +
                "\nestado=" + estado +
                "\ndivisa=" + divisa +
                '}';
    }
}
