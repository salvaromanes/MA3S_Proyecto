package ma3s.fintech.ejb.excepciones;

public class CuentaExistenteException extends Exception{
    public CuentaExistenteException(){}

    public CuentaExistenteException(String msg){
        super(msg);
    }
}
