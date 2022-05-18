package ma3s.fintech.ejb.excepciones;

public class CuentaReferenciaYaExisteException extends  Exception{

    public CuentaReferenciaYaExisteException(){

    }

    public CuentaReferenciaYaExisteException(String message){
        super(message);
    }

}
