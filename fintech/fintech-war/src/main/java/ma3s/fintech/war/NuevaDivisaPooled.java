package ma3s.fintech.war;

import ma3s.fintech.Divisa;
import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.excepciones.DatosIncorrectosException;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "addDivisaPooled")
@RequestScoped
public class NuevaDivisaPooled {
    @Inject
    private GestionGetCuentas getCuentas;

    @Inject
    GestionAperturaCuenta aperturaCuenta;

    @Inject
    private Sesion sesion;

    private String divisa;

    public String addDivisa(){
        try {
            Divisa div = getCuentas.getDivisa(divisa);
            aperturaCuenta.addDivisaPooled(sesion.getPooled(), div);
        } catch (DatosIncorrectosException | DivisaExistenteException e) {
            e.printStackTrace();
        }
        return "MostrarDatosCuentaPooled.xhtml?faces-redirect=true";
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }
}
