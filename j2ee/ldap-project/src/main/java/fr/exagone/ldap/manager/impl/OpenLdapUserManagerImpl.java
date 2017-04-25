package fr.exagone.ldap.manager.impl;

import org.springframework.ldap.core.LdapTemplate;

import fr.exagone.ldap.bean.LDapUser;
import fr.exagone.ldap.manager.LDapUserManager;
import fr.exagone.ldap.mapper.LdapUserMapper;
import fr.exagone.ldap.utils.LdapUtils;

public class OpenLdapUserManagerImpl implements LDapUserManager {

	private LdapTemplate ldapTemplate;
	private LdapUserMapper ldapUserMapper;
	
	public LDapUser getLDapUser(final String ldapUid) {
		return (LDapUser)ldapTemplate.lookup(LdapUtils.getInstance().getName(ldapUid), ldapUserMapper);
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	public void setLdapUserMapper(LdapUserMapper ldapUserMapper) {
		this.ldapUserMapper = ldapUserMapper;
	}

}
