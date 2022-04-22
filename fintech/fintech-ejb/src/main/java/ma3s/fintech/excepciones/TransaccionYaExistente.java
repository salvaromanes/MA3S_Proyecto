package ma3s.fintech.excepciones;

public class TransaccionYaExistente extends  Exception{

    public TransaccionYaExistente(){

    }

    public TransaccionYaExistente(String message){
        super(message);
    }

}
