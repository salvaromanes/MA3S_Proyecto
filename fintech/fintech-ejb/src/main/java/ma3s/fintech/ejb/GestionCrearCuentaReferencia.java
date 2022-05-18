package ma3s.fintech.ejb;

import ma3s.fintech.Referencia;
import ma3s.fintech.ejb.excepciones.CuentaReferenciaYaExisteException;

public interface GestionCrearCuentaReferencia {

    public void anyadirCuentaReferencia(Referencia referencia) throws CuentaReferenciaYaExisteException;

}
