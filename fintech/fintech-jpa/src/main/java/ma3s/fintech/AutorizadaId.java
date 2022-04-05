package ma3s.fintech;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class AutorizadaId implements Serializable {
    private Long autorizadaId;
    private Long empresaId;

    public AutorizadaId() {

    }

    public Long getPersonaAutorizadaId() {
        return autorizadaId;
    }

    public void setPersonaAutorizadaId(Long personaAutorizadaId) {
        autorizadaId = personaAutorizadaId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        empresaId = empresaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorizadaId that = (AutorizadaId) o;
        return autorizadaId.equals(that.autorizadaId) && empresaId.equals(that.empresaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autorizadaId, empresaId);
    }
}
