package ma3s.fintech.ejb.excepciones;

public class NoEsAdministrativoException extends  Exception{

    public NoEsAdministrativoException(){

    }

    public NoEsAdministrativoException(String message){
        super(message);
    }

}
