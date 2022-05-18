package ma3s.fintech.ejb.excepciones;

public class PooledException extends  Exception{

    public PooledException(){

    }

    public PooledException(String message){
        super(message);
    }

}
