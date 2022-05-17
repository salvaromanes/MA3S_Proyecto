package ma3s.fintech.war;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Username {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        this.username = u;
    }
}
