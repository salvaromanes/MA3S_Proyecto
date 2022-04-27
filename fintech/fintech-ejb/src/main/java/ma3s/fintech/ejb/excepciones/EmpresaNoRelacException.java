package ma3s.fintech.ejb.excepciones;

public class EmpresaNoRelacException extends  AccesoException{
    public EmpresaNoRelacException(){

    }

    public EmpresaNoRelacException(String msg){
        super(msg);
    }
}
