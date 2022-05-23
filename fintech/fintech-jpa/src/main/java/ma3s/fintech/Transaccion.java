package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    protected String idUnico;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInstruccion;
    @Column(nullable = false)
    private Double cantidad;
    @Temporal(TemporalType.DATE)
    private Date fechaEjecucion;
    @Column(nullable = false)
    private String tipo;
    private Double comision;
    private Boolean internacional;

    @ManyToOne
    private Cuenta cuentaDestino;

    @ManyToOne
    private Cuenta cuentaOrigen;

    @ManyToOne
    private Divisa divisaEmisor;

    @ManyToOne
    private Divisa divisaReceptor;

    public Transaccion(){ }

    public String getIdUnico(){
        return idUnico;
    }

    public void setIdUnico(String id){
        idUnico = id;
    }

    public Date getFechaInstruccion(){
        return fechaInstruccion;
    }

    public void setFechaInstruccion(Date fecha){
        fechaInstruccion = fecha;
    }

    public Double getCantidad(){
        return cantidad;
    }

    public void setCantidad(Double cant){
        cantidad = cant;
    }

    public Date getFechaEjecucion(){
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fecha){
        fechaEjecucion = fecha;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tip){
        tipo = tip;
    }

    public Double getComision(){
        return comision;
    }

    public void setComision(Double c){
        comision = c;
    }

    public Boolean getInternacional(){
        return internacional;
    }

    public void setInternacional(Boolean i){
        internacional = i;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Divisa getDivisaEmisor() {
        return divisaEmisor;
    }

    public void setDivisaEmisor(Divisa divisaEmisor) {
        this.divisaEmisor = divisaEmisor;
    }

    public Divisa getDivisaReceptor() {
        return divisaReceptor;
    }

    public void setDivisaReceptor(Divisa divisaReceptor) {
        this.divisaReceptor = divisaReceptor;
    }

    @Override
    public boolean equals(Object o) {
        boolean x = false;
        if(o instanceof Transaccion){
            Transaccion aux = (Transaccion) o;
            x = this.idUnico.equalsIgnoreCase(aux.getIdUnico());
        }
        return x;
    }

    @Override
    public int hashCode() {
        return this.idUnico.toUpperCase().hashCode() ;
    }

    @Override
    public String toString() {
        return "Transaccion{\n" +
                "id_unico=" + idUnico +
                "\nfechaInstruccion=" + fechaInstruccion +
                "\ncantidad=" + cantidad +
                "\nfechaEjecucion=" + fechaEjecucion +
                "\ntipo=" + tipo +
                "\ncomision=" + comision +
                "\ninternacional=" + internacional +
                "\ncuentaDestino=" + cuentaDestino +
                "\ncuentaOrigen=" + cuentaOrigen +
                "\ndivisaEmisor=" + divisaEmisor +
                "\ndivisaReceptor=" + divisaReceptor +
                '}';
    }
}
