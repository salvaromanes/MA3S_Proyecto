package ma3s.fintech.ejb.excepciones;

public class TransaccionYaExistente extends  Exception{

    public TransaccionYaExistente(){

    }

    public TransaccionYaExistente(String message){
        super(message);
    }

}
