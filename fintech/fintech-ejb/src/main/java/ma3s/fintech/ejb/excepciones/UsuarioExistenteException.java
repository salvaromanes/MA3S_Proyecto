package ma3s.fintech.ejb.excepciones;

public class UsuarioExistenteException extends  Exception {
    public UsuarioExistenteException(){ }

    public UsuarioExistenteException(String message){
        super(message);
    }
}
