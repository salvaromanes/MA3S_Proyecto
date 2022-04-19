import org.junit.Before;

import javax.naming.NamingException;

public class Usuarios {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechTest";

    @Before
    public void setup() throws NamingException {
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }
}
