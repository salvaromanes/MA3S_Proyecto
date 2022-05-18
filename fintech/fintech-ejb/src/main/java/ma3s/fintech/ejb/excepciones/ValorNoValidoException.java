package ma3s.fintech.ejb.excepciones;

public class ValorNoValidoException extends AccesoException{
    public ValorNoValidoException(){super();}

    public ValorNoValidoException(String msg){super(msg);}
}
