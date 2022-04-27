package ma3s.fintech.ejb.excepciones;

public class CuentaNoExistenteException extends Exception{
    public CuentaNoExistenteException(){}

    public CuentaNoExistenteException(String msg){super(msg);}
}
