package rest.classes;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
//@JsonInclude(Include.NON_NULL)
public class PadreProducts {
    private List<Products> products;

    public List<Products> getProducts() {
        return products;
    }
    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
