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
    private String razon_social;


    public Empresa(){
        super();
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    @Override
    public String toString() {
        return super.toString() + "Empresa{\n" +
                "razon_social=" + razon_social +
                '}';
    }
}
