/*
 * Created on 12 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.citesciences.ldap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Administrateur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LDAPProperties {

    private static LDAPProperties properties = null;

    private static Map props = new HashMap();

    public static LDAPProperties getInstance() {
        if (properties == null) {
            properties = new LDAPProperties();
        }
        return properties;
    }

    private Properties getProps(String identifier) {
        Properties properties = (Properties) props.get(identifier);
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(LDAPProperties.class.getResourceAsStream(identifier));
            } catch (IOException e) {
                return null;
            }
        }
        return properties;
    }
    
    public Properties getLDAPProperties() {
		return getProps("/ldap.properties");
	}
}
