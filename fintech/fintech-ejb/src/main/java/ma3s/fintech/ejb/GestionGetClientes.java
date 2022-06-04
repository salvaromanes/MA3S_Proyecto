package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.DatosIncorrectosException;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;

import java.util.List;

public interface GestionGetClientes {
    public List<Cliente> getClientes();

    public Cliente devolverCliente(String identificacion) throws ClienteNoExisteException;

    public Individual devolverIndividual(Long id) throws PersonaNoExisteException;

    public Empresa devolverEmpresa(Long id) throws EmpresaNoExistenteException;

    public Individual devolverIndividual(String identificacion) throws PersonaNoExisteException;

    public Empresa devolverEmpresa(String identificacion) throws EmpresaNoExistenteException;

    public List<Empresa> getEmpresas();

    public List<Individual> getIndividuales();

    public PAutorizada getPAutorizada(String identificacion) throws EmpresaNoExistenteException;

    public List<PAutorizada> getPAutorizadas();

    String getFechNac(Long id);

    String getFechNacPA(Long id);

    String getApellidos(Long id);

    String getNombre(Long id) throws DatosIncorrectosException;
}