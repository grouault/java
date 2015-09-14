package tudu.domain.model;

import java.io.Serializable;

/**
 * A user's role.
 * 
 * @author Julien Dubois
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -5636845397516495671L;

    public String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
