package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DepositadaEn {
    @Id
    private Double saldo;


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
