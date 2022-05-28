package ma3s.fintech.ejb.excepciones;

public class OperacionNoValida extends Exception{
    public OperacionNoValida(){

    }

    public OperacionNoValida(String msg){
        super(msg);
    }
}
