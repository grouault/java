package fr.citesciences.bonita.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;
import org.junit.Before;
import org.junit.Test;

import fr.citesciences.bonita.exceptions.TechnicalException;

public class BonitaUserGroupmanagerTest {
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("javax.net.ssl.trustStore","C:\\partition D\\tools\\bonita\\ssl\\intranet.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "intranet");
		System.setProperty("java.security.auth.login.config", "C:\\jaas-standard.cfg");
		
	}

	@Test
	public void testBonitaManager() throws UserNotFoundException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, LoginException, IOException, TechnicalException {
		
		String serverbonita = "https://portail.intranet.citepro.cite-sciences.fr";
		Map<String, String> map = new HashMap<String, String>();
		
		try{
			BonitaUserGroupManager bonitaUserGroupManager = new BonitaUserGroupManager();
			
			map.put("server.url", serverbonita);
			map.put("application.name", "bonita");

			APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, map);
			final LoginAPI loginAPI = TenantAPIAccessor.getLoginAPI();
			APISession session = loginAPI.login("nataf1", "Mortimer11");
			
			
			bonitaUserGroupManager.affectRoleToUser(session,"varasse", "/Salarie", "membre");
			
			
		}catch(Exception ex){
			
		}
	}
}
