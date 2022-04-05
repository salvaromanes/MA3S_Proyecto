package ma3s.fintech;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Empresa extends Cliente implements Serializable {

    @Column(nullable = false)
    private String razonSocial;


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
