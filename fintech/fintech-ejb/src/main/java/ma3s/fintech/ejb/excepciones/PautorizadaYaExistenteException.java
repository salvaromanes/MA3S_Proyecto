package ma3s.fintech.ejb.excepciones;

public class PautorizadaYaExistenteException extends Exception{
    public PautorizadaYaExistenteException(){

    }

    public PautorizadaYaExistenteException(String e){
        super(e);
    }
}
