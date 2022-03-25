package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class DepositadaEn {

    private Double saldo;

    @Id
    @ManyToOne
    @JoinColumn(name = "pooled")
    private Pooled pooled;

    @Id
    @ManyToOne
    @JoinColumn(name = "referencia")
    private Referencia referencia;

    public DepositadaEn(){

    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositadaEn that = (DepositadaEn) o;
        return saldo.equals(that.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saldo);
    }

    @Override
    public String toString() {
        return "DepositadaEn{" +
                "saldo=" + saldo +
                '}';
    }
}
