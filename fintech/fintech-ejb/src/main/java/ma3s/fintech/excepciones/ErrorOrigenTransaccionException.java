package ma3s.fintech.excepciones;

public class ErrorOrigenTransaccionException extends  Exception{

    public ErrorOrigenTransaccionException(){

    }

    public ErrorOrigenTransaccionException(String message){
        super(message);
    }

}
