package ma3s.fintech.excepciones;

public class CuentaAbiertaException extends Exception{
    public CuentaAbiertaException(){}

    public CuentaAbiertaException(String msg){
        super(msg);
    }
}
