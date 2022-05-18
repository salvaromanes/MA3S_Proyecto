package ma3s.fintech.ejb.excepciones;

public class DivisaExistenteException extends Exception {
    public DivisaExistenteException(){}

    public DivisaExistenteException(String msg){
        super(msg);
    }
}
