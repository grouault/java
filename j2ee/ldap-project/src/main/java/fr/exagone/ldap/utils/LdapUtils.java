package fr.exagone.ldap.utils;

import javax.naming.Name;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.DistinguishedName;

public class LdapUtils {

	private static Log logger = LogFactory.getLog(LdapUtils.class);
	
	private static LdapUtils instance = null;
	
	public static LdapUtils getInstance() {
		synchronized (LdapUtils.class) {
			if (instance == null) {
				instance = new LdapUtils();
			}
		}
		return instance;
	}
	
	public Name getName(final String ldapUid) {
		DistinguishedName dn = new DistinguishedName();
//		dn.add("dc", "fr");
//		dn.add("dc", "exagone");
		dn.add("ou", "People");
		dn.add("cn", ldapUid);
		if (logger.isDebugEnabled()) {
			logger.debug( "dn = " + dn);
		}
		return dn;
	}
	
}
