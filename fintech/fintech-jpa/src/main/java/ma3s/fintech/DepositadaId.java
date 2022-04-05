package ma3s.fintech;

import java.io.Serializable;
import java.util.Objects;

public class DepositadaId implements Serializable {
    private String ibanPooled;
    private String ibanReferencia;

    public DepositadaId() {
    }

    public String getIbanPooled() {
        return ibanPooled;
    }

    public void setIbanPooled(String ibanPooled) {
        this.ibanPooled = ibanPooled;
    }

    public String getIbanReferencia() {
        return ibanReferencia;
    }

    public void setIbanReferencia(String ibanReferencia) {
        this.ibanReferencia = ibanReferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositadaId that = (DepositadaId) o;
        return ibanPooled.equals(that.ibanPooled) && ibanReferencia.equals(that.ibanReferencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ibanPooled, ibanReferencia);
    }

    @Override
    public String toString() {
        return "DepositadaId{\n" +
                "ibanPooled='" + ibanPooled + '\n' +
                "ibanReferencia='" + ibanReferencia +
                '}';
    }
}
