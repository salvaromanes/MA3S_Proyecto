package ma3s.fintech;

import java.io.Serializable;
import java.util.Objects;

public class AutorizadaId implements Serializable {
    private Long AutorizadaId;
    private Long EmpresaId;

    public AutorizadaId() {

    }

    public Long getAutorizadaId() {
        return AutorizadaId;
    }

    public void setAutorizadaId(Long autorizadaId) {
        AutorizadaId = autorizadaId;
    }

    public Long getEmpresaId() {
        return EmpresaId;
    }

    public void setEmpresaId(Long empresaId) {
        EmpresaId = empresaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorizadaId that = (AutorizadaId) o;
        return AutorizadaId.equals(that.AutorizadaId) && EmpresaId.equals(that.EmpresaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AutorizadaId, EmpresaId);
    }
}
