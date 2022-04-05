package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Empresa extends Cliente implements Serializable {

    @Column(nullable = false)
    private String razonSocial;

    @OneToMany(mappedBy = "empresaId")
    private List<Autorizacion> autorizaciones;

    public Empresa(){
        super();
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Override
    public String toString() {
        return super.toString() + "Empresa{\n" +
                "razon_social=" + razonSocial +
                '}';
    }
}
