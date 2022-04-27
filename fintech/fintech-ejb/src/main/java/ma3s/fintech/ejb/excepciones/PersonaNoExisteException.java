package ma3s.fintech.ejb.excepciones;

public class PersonaNoExisteException extends Exception {
    public PersonaNoExisteException(){

    }

    public PersonaNoExisteException(String msg){
        super(msg);
    }
}
