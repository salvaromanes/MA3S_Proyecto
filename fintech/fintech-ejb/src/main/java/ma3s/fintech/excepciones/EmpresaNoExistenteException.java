package ma3s.fintech.excepciones;

public class EmpresaNoExistenteException extends Exception{
    public EmpresaNoExistenteException(){

    }

    public EmpresaNoExistenteException(String msg){
        super (msg);
    }
}
