package ma3s.fintech.ejb.excepciones;

public class DatosIncorrectosException extends  Exception{

    public DatosIncorrectosException(){

    }

    public DatosIncorrectosException(String message){
        super(message);
    }

}
