package ma3s.fintech.ejb.excepciones;

public class EstadoNoValidoException extends  Exception{

    public EstadoNoValidoException(){

    }

    public EstadoNoValidoException(String message){
        super(message);
    }

}
