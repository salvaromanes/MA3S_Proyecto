package ma3s.fintech.ejb.excepciones;

public class IndividualNoExistenteException extends Exception{
    public IndividualNoExistenteException(){

    }

    public IndividualNoExistenteException(String msg){
        super(msg);
    }
}
