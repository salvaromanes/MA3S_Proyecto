package ma3s.fintech.war;

import ma3s.fintech.Divisa;
import ma3s.fintech.ejb.GestionGetCuentas;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class EnumDivisas implements Serializable {
    private Map<String,String> divisas;

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @PostConstruct
    public void init() {
        divisas = new HashMap<>();
        List<Divisa> lista = gestionGetCuentas.getDivisas();

        for (Divisa d : lista){
            divisas.put(d.getAbreviatura(),d.getAbreviatura());
        }
    }

    public Map<String, String> getDivisas() {
        return divisas;
    }
}
