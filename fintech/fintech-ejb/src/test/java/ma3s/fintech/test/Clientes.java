package ma3s.fintech.test;

import org.junit.Before;

import javax.naming.NamingException;

public class Clientes {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    @Before
    public void setup() throws NamingException {
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }
}
