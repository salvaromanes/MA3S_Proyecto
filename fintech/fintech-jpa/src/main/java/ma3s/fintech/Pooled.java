package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Pooled extends Fintech implements Serializable {
    private Double Saldo;
    public Pooled(){
        super();
    }

    @ManyToMany(mappedBy = "cuentas_pooled")
    @JoinTable(name = "POOLED_REFERENCIA", joinColumns = @JoinColumn(name = "IBAN_POOLED"),inverseJoinColumns = @JoinColumn(name = "IBAN_REFERENCIA") )
    private List<Referencia> referencias;



    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }

    public List<Referencia> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<Referencia> referencias) {
        this.referencias = referencias;
    }

    @Override
    public String toString() {
        return super.toString() + " Cuenta Pooled{" +
                "Saldo=" + Saldo +
                '}';
    }
}
