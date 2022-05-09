package ma3s.fintech.ejb.excepciones;

public class ContraseñaIncorrectaException extends  AccesoException{

    public  ContraseñaIncorrectaException(){super();

    }

    public ContraseñaIncorrectaException(String message){
        super(message);
    }
}
