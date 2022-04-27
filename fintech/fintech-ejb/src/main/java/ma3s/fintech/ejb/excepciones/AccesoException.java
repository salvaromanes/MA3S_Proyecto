package ma3s.fintech.ejb.excepciones;

public class AccesoException  extends  Exception{

    public  AccesoException(){

    }

    public AccesoException(String message){
        super(message);
    }

}
