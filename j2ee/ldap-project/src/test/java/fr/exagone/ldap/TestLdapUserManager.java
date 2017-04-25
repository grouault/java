package fr.exagone.ldap;


import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.exagone.ldap.bean.LDapUser;
import fr.exagone.ldap.manager.impl.OpenLdapUserManagerImpl;


@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "estim-ldap-test-context.xml" })
public class TestLdapUserManager {

	private Log logger = LogFactory.getLog(TestLdapUserManager.class);

	@Resource(name = "uidUserSchmo")
    private String uidUserSchmo;
	
    @Autowired
    private OpenLdapUserManagerImpl ldapUserManager;
	
    @Test
    public final void getUserSchmo() {
    	LDapUser user = ldapUserManager.getLDapUser(uidUserSchmo);
    	logger.info("getUserSchmo : " + user);
    	Assert.assertNotNull(user);
    }
}
