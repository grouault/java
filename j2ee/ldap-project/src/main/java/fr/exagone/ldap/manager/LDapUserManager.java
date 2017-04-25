package fr.exagone.ldap.manager;

import fr.exagone.ldap.bean.LDapUser;

public interface LDapUserManager {

	LDapUser getLDapUser(final String ldapUid);
	
}
