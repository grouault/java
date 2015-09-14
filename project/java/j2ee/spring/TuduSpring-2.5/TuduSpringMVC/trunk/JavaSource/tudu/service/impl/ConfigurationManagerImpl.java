package tudu.service.impl;

import tudu.domain.dao.PropertyDAO;
import tudu.domain.model.Property;
import tudu.service.ConfigurationManager;

/**
 * Implementation of the tudu.service.ConfigurationManager interface.
 * 
 * @author Julien Dubois
 */
public class ConfigurationManagerImpl implements ConfigurationManager {

    private PropertyDAO propertyDAO = null;

    public void setPropertyDAO(PropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    /**
     * @see tudu.service.ConfigurationManager#getProperty(java.lang.String)
     */
    public Property getProperty(String key) {
        return propertyDAO.getProperty(key);
    }    

    /**
     * @see tudu.service.ConfigurationManager#updateEmailProperties(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void updateEmailProperties(String smtpHost, String smtpPort, String smtpUser, String smtpPassword,
            String smtpFrom) {
        
        Property hostProperty = propertyDAO.getProperty("smtp.host");
        hostProperty.setValue(smtpHost);
        propertyDAO.updateProperty(hostProperty);
        Property portProperty = propertyDAO.getProperty("smtp.port");
        portProperty.setValue(smtpPort);
        propertyDAO.updateProperty(portProperty);
        Property userProperty = propertyDAO.getProperty("smtp.user");
        userProperty.setValue(smtpUser);
        propertyDAO.updateProperty(userProperty);
        Property passwordProperty = propertyDAO.getProperty("smtp.password");
        passwordProperty.setValue(smtpPassword);
        propertyDAO.updateProperty(passwordProperty);
        Property fromProperty = propertyDAO.getProperty("smtp.from");
        fromProperty.setValue(smtpFrom);
        propertyDAO.updateProperty(fromProperty);
    }

}
