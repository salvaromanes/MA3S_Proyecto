package rest.classes;

import java.util.Date;

public class SearchParameters {

    private Date startPeriod;
    private Date endPeriod;

    private Name name;


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
