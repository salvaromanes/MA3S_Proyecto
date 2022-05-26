package rest.classes;

public class Name {

    private String lastName;
    private String firstName;

    public Name(String nombre, String apellido){
        this.firstName = nombre;
        this.lastName = apellido;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "\"name\": {" +
                "\t" + "\"lastName\": " + "\"" +lastName +  "\"" +"\n" +
                '}';
    }
}
