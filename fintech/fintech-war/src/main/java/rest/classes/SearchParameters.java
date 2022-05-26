package rest.classes;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Date;

public class SearchParameters {

    private String startPeriod;
    private String endPeriod;
    private Name name;

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getJson(){
        Jsonb builder = JsonbBuilder.create();
        String str = builder.toJson(this);
        return str;
    }

    @Override
    public String toString() {
        return "{ \n" +
                "\"SearchParameters\": {" + "\n" +
                "\t" + name.toString() +",\n" +
                "\"startPeriod\": " + "\"" + startPeriod +"\"" +
                ",\n"+
                "\"endPeriod\": " +"\"" + endPeriod + "\"" +"\n" +
                '}';
    }
}
