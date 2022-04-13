package ma3s.fintech.excepciones;

public class CuentaNoExistenteException extends Exception{
    public CuentaNoExistenteException(){}

    public CuentaNoExistenteException(String msg){super(msg);}
}
