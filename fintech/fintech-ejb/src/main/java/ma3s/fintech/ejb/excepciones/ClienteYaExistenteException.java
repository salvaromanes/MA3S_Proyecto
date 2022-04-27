package ma3s.fintech.ejb.excepciones;

public class ClienteYaExistenteException extends Exception{
    public ClienteYaExistenteException(){

    }

    public ClienteYaExistenteException(String e){
        super(e);
    }
}
