package ma3s.fintech.war;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class Password implements Serializable {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        this.password = p;
    }
}
