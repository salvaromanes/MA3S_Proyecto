package ma3s.fintech;

import com.sun.org.apache.xpath.internal.operations.Div;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Pooled extends Fintech implements Serializable {
    private Double Saldo;
    public Pooled(){
        super();
    }

    @ManyToOne()
    private Divisa divisa;

    @OneToMany(mappedBy = "ibanPooled")
    private List<DepositadaEn> depositos;

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }

    public List<DepositadaEn> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositadaEn> depositos) {
        this.depositos = depositos;
    }

    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    @Override
    public String toString() {
        return super.toString() + " Cuenta Pooled{\n" +
                "Saldo=" + Saldo +
                '}';
    }
}
