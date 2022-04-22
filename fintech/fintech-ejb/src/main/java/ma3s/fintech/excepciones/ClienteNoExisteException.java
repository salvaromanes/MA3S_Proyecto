package ma3s.fintech.excepciones;

public class ClienteNoExisteException extends Exception{
    public ClienteNoExisteException(){}

    public ClienteNoExisteException(String msg){
        super(msg);
    }
}
