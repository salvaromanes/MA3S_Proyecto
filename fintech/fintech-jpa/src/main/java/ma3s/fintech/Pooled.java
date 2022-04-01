package ma3s.fintech;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Pooled extends Fintech implements Serializable {
    public Pooled(){
        super();
    }



    @Override
    public String toString() {
        return  "Cuenta Pooled: " + super.toString();
    }
}
