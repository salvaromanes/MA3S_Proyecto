package ma3s.fintech.ejb.excepciones;

public class CampoVacioException extends Exception{
    public CampoVacioException(){

    }

    public CampoVacioException(String msg){
        super(msg);
    }
}
