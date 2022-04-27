package ma3s.fintech.ejb.excepciones;

public class ErrorOrigenTransaccionException extends  Exception{

    public ErrorOrigenTransaccionException(){

    }

    public ErrorOrigenTransaccionException(String message){
        super(message);
    }

}
