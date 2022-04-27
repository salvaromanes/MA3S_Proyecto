package ma3s.fintech.ejb.excepciones;

public class SaldoNoSuficiente extends  Exception{

    public SaldoNoSuficiente(){

    }

    public SaldoNoSuficiente(String message){
        super(message);
    }

}
