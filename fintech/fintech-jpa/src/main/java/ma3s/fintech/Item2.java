package ma3s.fintech;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item2 {

    @Id
    @GeneratedValue
    private Long nombre;
    private String title;
    private Float price;
    private String description;

    public Item2() {
        super();
    }

    public Long getId() {
        return this.nombre;
    }

    public void setId(Long id) {
        this.nombre = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}