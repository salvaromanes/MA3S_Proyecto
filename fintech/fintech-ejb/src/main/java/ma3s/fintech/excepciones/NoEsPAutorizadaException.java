package ma3s.fintech.excepciones;

public class NoEsPAutorizadaException extends  Exception{

    public NoEsPAutorizadaException(){

    }

    public NoEsPAutorizadaException(String message){
        super(message);
    }

}
