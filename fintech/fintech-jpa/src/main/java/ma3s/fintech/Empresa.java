package ma3s.fintech;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Empresa extends Cliente{
    private String razon_social;


    public Empresa(){
        super();
    }

    public Empresa(String id, String identificacion, String tipo_cliente, Date fecha_alta, Date fecha_baja, String direccion, String ciudad, String codigo_postal, String pais, String razon_social) throws Exception {
        super(id, identificacion, tipo_cliente, fecha_alta, fecha_baja, direccion, ciudad, codigo_postal, pais);
        this.razon_social = razon_social;
    }


    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    @Override
    public String toString() {
        return super.toString() +"\n"+
                "Empresa \n " +
                "razon_social='" + razon_social;
    }
}
