package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.*;

public interface GestionAperturaCuenta {
    //public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void abrirCuentaPooled(String iban, String swift, String usuario, String divisa, String identificacion) throws DivisaExistenteException, CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, ClienteNoExisteException;
    public void abrirCuentaSegregate(String iban, String swift, String usuario, String identificacion) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, ClienteNoExisteException;

    public void referenciaParaPooled(String ibanPooled, String divisaAbrev) throws DivisaExistenteException, PooledException, DatosIncorrectosException;
    public void referenciaParaSegregada(String ibanReferencia, String ibanSegregada, String divisaAbrev) throws SegregadaException, DivisaExistenteException, ReferenciaException, DatosIncorrectosException;

    public Pooled obtenerDatosCuentaPooled(String IBAN);
    public Segregada obtenerDatosCuentaSegregada(String IBAN);
}
