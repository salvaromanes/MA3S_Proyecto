package ma3s.fintech.excepciones;

public class EmpresaNoRelacException extends  AccesoException{
    public EmpresaNoRelacException(){

    }

    public EmpresaNoRelacException(String msg){
        super(msg);
    }
}
