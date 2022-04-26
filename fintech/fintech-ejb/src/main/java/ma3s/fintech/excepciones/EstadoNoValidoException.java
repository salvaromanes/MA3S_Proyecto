package ma3s.fintech.excepciones;

public class EstadoNoValidoException extends  Exception{

    public EstadoNoValidoException(){

    }

    public EstadoNoValidoException(String message){
        super(message);
    }

}
