package ma3s.fintech.ejb.excepciones;

public class ErrorInternoException extends Exception {
    public  ErrorInternoException(){}

    public ErrorInternoException(String message){
        super(message);
    }
}
