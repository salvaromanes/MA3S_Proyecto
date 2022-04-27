package ma3s.fintech.ejb.excepciones;

public class EmpresaNoExistenteException extends Exception{
    public EmpresaNoExistenteException(){

    }

    public EmpresaNoExistenteException(String msg){
        super (msg);
    }
}
