package ma3s.fintech.excepciones;

public class CuentaNoVacia extends Exception{
    public CuentaNoVacia(){}

    public CuentaNoVacia(String msg){super(msg);}
}
