package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Referencia extends Cuenta implements Serializable {
    @Column(nullable = false)
    private String nombreBanco;

    private String sucursal;
    private String pais;

    @Column(nullable = false)
    private double saldo;
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;
    private String estado;

    @OneToOne
    private Segregada segregada;

    @ManyToOne
    private Divisa divisa;

    @OneToMany(mappedBy = "ibanReferencia")
    private List<DepositadaEn> depositos;

    public Referencia(){
        super();
    }


    public String getNombreBanco() {
        return nombreBanco;
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

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public Segregada getSegregada() {
        return segregada;
    }

    public void setNombreBanco(String n) {
        nombreBanco =n;
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

    public void setFechaApertura(Date f){
        fechaApertura =f;
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

    public List<DepositadaEn> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositadaEn> depositos) {
        this.depositos = depositos;
    }

    @Override
    public String toString() {
        return  super.toString() + "Referencia{\n" +
                "nombrebanco=" + nombreBanco +
                "\nsucursal=" + sucursal +
                "\npais=" + pais +
                "\nsaldo=" + saldo +
                "\nfechaApertura=" + fechaApertura +
                "\nestado=" + estado +
                "\ndivisa=" + divisa +
                '}';
    }
}
