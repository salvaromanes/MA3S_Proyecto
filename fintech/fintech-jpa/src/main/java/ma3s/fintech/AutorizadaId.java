package ma3s.fintech;

import java.io.Serializable;
import java.util.Objects;

public class AutorizadaId implements Serializable {
    private Long autorizadaId;
    private Long empresaId;

    public AutorizadaId() {

    }

    public Long getIdAutorizado() {
        return autorizadaId;
    }

    public void setIdAutorizado(Long personaAutorizadaId) {
        autorizadaId = personaAutorizadaId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresa) {
        empresaId = empresa;
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

    @Override
    public String toString() {
        return "AutorizadaId{" +
                "\npAutorizadaId=" + autorizadaId +
                "\nempresaId=" + empresaId +
                '}';
    }
}
