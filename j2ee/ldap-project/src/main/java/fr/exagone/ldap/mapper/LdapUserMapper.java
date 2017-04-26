package fr.exagone.ldap.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.AttributesMapper;


import fr.exagone.ldap.bean.LDapUser;

public class LdapUserMapper implements AttributesMapper {
	
    private Log logger = LogFactory.getLog(LdapUserMapper.class);

	public Object mapFromAttributes(Attributes attrs) throws NamingException {
		LDapUser user = new LDapUser();
        try {
            if (attrs.get("sn") != null) {
                user.setNom((String) attrs.get("sn").get());
            }
            if (attrs.get("cn") != null) {
                user.setNomComplet((String) attrs.get("cn").get());
            }
            if (attrs.get("givenName") != null) {
                user.setPrenom((String) attrs.get("givenName").get());
            }
            if (attrs.get("mail") != null) {
                user.setMail((String) attrs.get("mail").get());
            }
            if (attrs.get("employeeNumber") != null) {
                user.setMatricule((String) attrs.get("employeeNumber").get());
            }
        } catch (NamingException exception) {
            logger.error("une erreur de mapping a été rencontré", exception);
            throw exception;
        }
		return user;
	}

}
