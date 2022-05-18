package ma3s.fintech.ejb.excepciones;

public class SaldoException extends  Exception{

    public SaldoException(){

    }

    public SaldoException(String message){
        super(message);
    }

}
