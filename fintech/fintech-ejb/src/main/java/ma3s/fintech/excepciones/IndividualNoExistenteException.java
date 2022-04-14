package ma3s.fintech.excepciones;

public class IndividualNoExistenteException extends Exception{
    public IndividualNoExistenteException(){

    }

    public IndividualNoExistenteException(String msg){
        super(msg);
    }
}
