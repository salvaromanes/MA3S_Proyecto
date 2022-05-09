package ma3s.fintech.ejb.excepciones;

public class UsuarioNoEncontradoException extends AccesoException{
    public UsuarioNoEncontradoException(){super();}

    public UsuarioNoEncontradoException(String msg){super(msg);}
}
