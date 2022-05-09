package ma3s.fintech.ejb.excepciones;

public class UsuarioIncorrectoException extends  AccesoException{
    public UsuarioIncorrectoException(){super();}

    public UsuarioIncorrectoException(String msg){super(msg);}
}
