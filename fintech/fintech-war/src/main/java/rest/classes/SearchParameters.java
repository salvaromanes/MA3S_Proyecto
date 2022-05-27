package rest.classes;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Date;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchParameters {

    //@XmlTransient
    //@JsonbTransient
    private Name name;

    //private SearchParameters searchParameters;

    @JsonbDateFormat("yyyy-MM-dd")
    private Date startPeriod;
    @JsonbDateFormat("yyyy-MM-dd")
    private Date endPeriod;

    public Date getStartPeriod() {
        return startPeriod;
    }
    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }
    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }
}