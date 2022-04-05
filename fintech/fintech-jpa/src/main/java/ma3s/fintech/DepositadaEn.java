package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(DepositadaId.class)
public class DepositadaEn implements Serializable {

    @Column(nullable = false)
    private Double saldo;

    @Id
    @ManyToOne
    private Pooled ibanPooled;

    @Id
    @ManyToOne
    private Referencia ibanReferencia;

    public DepositadaEn(){

    }

    public Double getSaldo() {
        return saldo;
    }

    public Referencia getReferencia() {
        return ibanReferencia;
    }

    public Pooled getPooled() {
        return ibanPooled;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setReferencia(Referencia referencia) {
        this.ibanReferencia = referencia;
    }

    public void setPooled(Pooled pooled) {
        this.ibanPooled = pooled;
    }

    public Pooled getIbanPooled() {
        return ibanPooled;
    }

    public void setIbanPooled(Pooled ibanPooled) {
        this.ibanPooled = ibanPooled;
    }

    public Referencia getIbanReferencia() {
        return ibanReferencia;
    }

    public void setIbanReferencia(Referencia ibanReferencia) {
        this.ibanReferencia = ibanReferencia;
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
        return "DepositadaEn{\n" +
                "saldo=" + saldo +
                "\npooled=" + ibanPooled +
                "\nreferencia=" + ibanReferencia +
                '}';
    }
}
