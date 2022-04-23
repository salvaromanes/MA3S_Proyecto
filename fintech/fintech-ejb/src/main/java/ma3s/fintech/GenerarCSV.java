package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerarCSV implements GestionGenerarCSV{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;


    @Override
    public void generarCSV(String usuario, String tipoInforme, Date ultimoReporte) throws PersonaNoExisteException, IOException {
        Usuario user = em.find(Usuario.class, usuario);
        Query query = em.createQuery("select c from Cliente c");
        List<Cliente> listaClientes = query.getResultList();

        if(!isAdministrativo(user.getUser())){
            throw new PersonaNoExisteException("El usuario " + usuario + " no existe");
        }

        CSVPrinter csv = null;
        if (tipoInforme.equals("Inicial") && listaClientes != null){
            csv = reporteInicial(listaClientes);
        }else if (tipoInforme.equals("Periodico") && listaClientes != null){
            csv = reportesPeriodicos(listaClientes);
        }

        // pasamos el csv a un string
        String stringCSV = csvString(csv);
    }

    private CSVPrinter reporteInicial(List<Cliente> lista) throws IOException {
        final StringWriter stringWriter = new StringWriter();
        CSVPrinter csv = new CSVPrinter(stringWriter,
                CSVFormat.DEFAULT.withHeader("Iban", "Apellidos", "Nombre", "Calle", "Ciudad",
                        "Codigo Postal", "Pais", "Numero de Identificacion", "Fecha Nacimiento"));

        for (Cliente c : lista) {
            List<Fintech> cuentasCliente = c.getCuentasFintech();
            for (Fintech f : cuentasCliente) {
                Calendar fechaActual = Calendar.getInstance();
                fechaActual.add(Calendar.YEAR, -5);
                Date fechaHace5 = fechaActual.getTime();
                if(f.getFechaApertura().compareTo(fechaHace5) == 0){
                    PAutorizada pa = c.getUser().getpAutorizada();
                    csv.printRecord(
                            // si algun valor es nulo debe guardarse como "noexistente"
                            comprobarNoNulo(f.getIban()),
                            comprobarNoNulo(pa.getApellidos()),
                            comprobarNoNulo(pa.getNombre()),
                            comprobarNoNulo(c.getDireccion()),
                            comprobarNoNulo(c.getCiudad()),
                            comprobarNoNulo(c.getCodigopostal()),
                            comprobarNoNulo(c.getPais()),
                            comprobarNoNulo(c.getIdentificacion()),
                            comprobarNoNuloFecha(pa.getFechaNacimiento())
                    );
                }
            }
        }
        return csv;
    }

    private CSVPrinter reportesPeriodicos(List<Cliente> lista) throws IOException {
        final StringWriter stringWriter = new StringWriter();
        CSVPrinter csv = new CSVPrinter(stringWriter,
                CSVFormat.DEFAULT.withHeader("Iban", "Apellidos", "Nombre", "Calle", "Ciudad",
                        "Codigo Postal", "Pais", "Numero de Identificacion", "Fecha Nacimiento"));

        for (Cliente c : lista) {
            List<Fintech> cuentasCliente = c.getCuentasFintech();
            for (Fintech f : cuentasCliente) {
                Calendar fechaActual = Calendar.getInstance();
                fechaActual.add(Calendar.YEAR, -5);
                Date fechaHace5 = fechaActual.getTime();
                if(f.getFechaApertura().compareTo(fechaHace5) == 0 &&
                        f.getFechaCierre().compareTo(fechaActual.getTime())<0){
                    PAutorizada pa = c.getUser().getpAutorizada();
                    csv.printRecord(
                            // si algun valor es nulo debe guardarse como "noexistente"
                            comprobarNoNulo(f.getIban()),
                            comprobarNoNulo(pa.getApellidos()),
                            comprobarNoNulo(pa.getNombre()),
                            comprobarNoNulo(c.getDireccion()),
                            comprobarNoNulo(c.getCiudad()),
                            comprobarNoNulo(c.getCodigopostal()),
                            comprobarNoNulo(c.getPais()),
                            comprobarNoNulo(c.getIdentificacion()),
                            comprobarNoNuloFecha(pa.getFechaNacimiento())
                    );
                }
            }
        }
        return csv;
    }

    private Object comprobarNoNulo(String dato) {
        if(dato.isEmpty()){
            dato = "noexistente";
        }
        return dato;
    }

    private Object comprobarNoNuloFecha(Date fecha) {
        Object fechaNula;
        if(fecha.toString().isEmpty() || fecha.toString() == null){
            fechaNula = "noexistente";
        }else{
            fechaNula = fecha;
        }
        return fechaNula;
    }

    private String csvString(CSVPrinter csv){
        String salida = null;
        String SEPARADOR = ",";
        BufferedReader bufferLectura = null;

        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader(String.valueOf(csv)));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con ","
                String[] campos = linea.split(SEPARADOR);
                salida += Arrays.toString(campos);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return salida;
    }

    // ¿el usuario es administrativo? sirve para poder gestionar las bajas
    @Override
    public boolean isAdministrativo(String usuario) throws PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);
        if(!user.getUser().equals(usuario)){
            throw new PersonaNoExisteException("El usuario " + usuario + " no existe");
        }
        return user.getEsAdmin();
    }
}
