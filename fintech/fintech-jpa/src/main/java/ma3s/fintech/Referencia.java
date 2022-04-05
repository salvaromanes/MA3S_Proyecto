package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Referencia extends Cuenta implements Serializable {
    @Column(nullable = false)
    private String nombrebanco;

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

    @ManyToMany(mappedBy = "referencias")
    private List<Pooled> cuentas_pooled;

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

    public Date getFechaApertura() {
        return fechaApertura;
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

    @Override
    public String toString() {
        return  super.toString() + "Referencia{\n" +
                "nombrebanco=" + nombrebanco +
                "\nsucursal=" + sucursal +
                "\npais=" + pais +
                "\nsaldo=" + saldo +
                "\nfecha_apertura=" + fechaApertura +
                "\nestado=" + estado +
                "\ndivisa=" + divisa +
                '}';
    }
}
