package ma3s.fintech.ejb;

import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.Transaccion;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import java.util.List;

public interface GestionListaCuenta {
   public Transaccion getTransaccion(String iban) ;
}
