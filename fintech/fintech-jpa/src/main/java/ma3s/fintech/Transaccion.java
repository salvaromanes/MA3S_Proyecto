package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaccion implements Serializable {
    @Id private String id_unico;
    @Temporal(TemporalType.DATE) private Date fechaInstruccion;
    private int cantidad;
    @Temporal(TemporalType.DATE) private Date fechaEjecucion;
    private String tipo;
    private double comision;
    private char internacional;
    private String cuenta_iban1;
    private String divisa_abreviatura1;
    private String divisa_abreviatura;
    private String cuenta_iban;

    // CONSTRUCTORES
    public Transaccion(){ }

    public Transaccion(String id_unico, Date fechaInstruccion, int cantidad, Date fechaEjecucion, String tipo,
                       double comision, char internacional, String cuenta_iban1, String divisa_abreviatura1,
                       String divisa_abreviatura, String cuenta_iban) throws Exception {

        // lanza excepcion si algun parametro está incompleto
        if( id_unico == null || fechaInstruccion == null || fechaEjecucion == null || cuenta_iban1 == null
                || divisa_abreviatura1 == null || divisa_abreviatura == null || cuenta_iban == null){
            throw new Exception("Parametros pasados erroneos");
        }

        this.id_unico = id_unico;
        this.fechaInstruccion = fechaInstruccion;
        this.cantidad = cantidad;
        this.fechaEjecucion = fechaEjecucion;
        this.tipo = tipo;
        this.comision = comision;
        this.internacional = internacional;
        this.cuenta_iban1 = cuenta_iban1;
        this.divisa_abreviatura1 = divisa_abreviatura1;
        this.cuenta_iban = cuenta_iban;
        this.divisa_abreviatura = divisa_abreviatura;
    }


    // GETTERS Y SETTERS

    // Get y Set de ID_UNICO
    public String getId_unico(){
        return id_unico;
    }

    public void setId_unico(String id){
        id_unico = id;
    }


    // Get y Set de FECHA INSTRUCCION
    public Date getFechaInstruccion(){
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Date fecha){
        fechaInstruccion = fecha;
    }


    // Get y Set de CANTIDAD
    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cant){
        cantidad = cant;
    }


    // Get y Set de FECHA EJECUCION
    public Date getFechaEjecucion(){
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fecha){
        fechaEjecucion = fecha;
    }


    // Get y Set de TIPO
    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tip){
        tipo = tip;
    }


    // Get y Set de COMISION
    public double getComision(){
        return comision;
    }

    public void setComision(double c){
        comision = c;
    }


    // Get y Set de INTERNACIONAL
    public char getInternacional(){
        return internacional;
    }

    public void setInternacional(char i){
        internacional = i;
    }


    // Get y Set de CUENTA IBAN 1
    public String getCuenta_iban1(){
        return cuenta_iban1;
    }

    public void setCuenta_iban1(String c){
        cuenta_iban1 = c;
    }


    // Get y Set de CUENTA IBAN
    public String getCuenta_iban(){
        return cuenta_iban;
    }

    public void setCuenta_iban(String c){
        cuenta_iban = c;
    }


    // Get y Set de DIVISA ABREVIATURA 1
    public String getDivisa_abreviatura1(){
        return divisa_abreviatura1;
    }

    public void setDivisa_abreviatura1(String d){
        divisa_abreviatura1 = d;
    }


    // Get y Set de DIVISA ABREVIATURA
    public String getDivisa_abreviatura(){
        return divisa_abreviatura;
    }

    public void setDivisa_abreviatura(String d){
        divisa_abreviatura = d;
    }


    // EQUALS, HASHCODE Y TOSTRING
    @Override
    public boolean equals(Object o) {
        boolean x = false;
        if(o instanceof Transaccion){
            Transaccion aux = (Transaccion) o;
            x = this.id_unico.equalsIgnoreCase(aux.getId_unico());
        }
        return x;
    }

    @Override
    public int hashCode() {
        return this.id_unico.toUpperCase().hashCode() ;
    }

    @Override
    public String toString() {
        return "Transaccion: \n" +
                "id: " + id_unico + "\n" + "," +
                "fechaInstruccion:" + fechaInstruccion + "\n" + "," +
                "cantidad: " + cantidad + "\n" + "," +
                "fechaEjecucion: " + fechaEjecucion + "\n" + "," +
                "tipo: " + tipo + "\n" + "," +
                "comision: " + comision + "\n" + "," +
                "internacional: " + internacional + "\n" + "," +
                "cuenta_iban1: " + cuenta_iban1 + "\n" + "," +
                "cuenta_iban: " + cuenta_iban + "\n" + "," +
                "divisa_abreviatura1: " + divisa_abreviatura1 + "\n" + "," +
                "divisa_abreviatura: " + divisa_abreviatura + "\n" ;
    }
}
