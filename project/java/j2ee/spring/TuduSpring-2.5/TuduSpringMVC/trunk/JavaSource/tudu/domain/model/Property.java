package tudu.domain.model;

import java.io.Serializable;

/**
 * A property, used to hold the application configuration.
 * 
 * @author Julien Dubois
 */
public class Property implements Serializable {

    private static final long serialVersionUID = 3434972458764657217L;

    private String key;
    
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
