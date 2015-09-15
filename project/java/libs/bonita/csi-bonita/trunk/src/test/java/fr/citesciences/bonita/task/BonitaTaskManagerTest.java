package fr.citesciences.bonita.task;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.platform.LoginException;
import org.junit.Before;
import org.junit.Test;

import fr.citesciences.bonita.bean.PortletBonitaLink;
import fr.citesciences.bonita.exceptions.TechnicalException;

/**
 * @author nataf1
 *
 */
public class BonitaTaskManagerTest {

	@Before
	public void setUp() throws Exception {
		System.setProperty("javax.net.ssl.trustStore","C:\\partition D\\tools\\bonita\\ssl\\intranet.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "intranet");
		System.setProperty("java.security.auth.login.config", "C:\\jaas-standard.cfg");
		
	}

	@Test
	public void testBonitaManagerProcessusDisponible() throws UserNotFoundException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, LoginException, IOException, TechnicalException {
		try{
		String username = "nataf1";
		String password = "bpm";
		String serverbonita = "http://localhost:8081";
		
		
		//String service = "https://portail.intranet.citepro.cite-sciences.fr/bonita/loginservice/";
		IBonitaTaskManager bManager = new BonitaTaskManager(serverbonita,username,password);
		
		List<PortletBonitaLink>  list =bManager.getProcessesAvailables(username);
		Iterator<PortletBonitaLink> iter = list.iterator();
		
		while(iter.hasNext()){
			PortletBonitaLink  link = iter.next();
			System.out.println(link.toString());
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	@Test
		public void testBonitaManagerProcessusTacheAssigned() throws UserNotFoundException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, LoginException, IOException, TechnicalException {
			try{
			String username = "serot";
			String password = "bpm";
			String serverbonita = "http://localhost:8081";
			
			
			//String service = "https://portail.intranet.citepro.cite-sciences.fr/bonita/loginservice/";
			IBonitaTaskManager bManager = new BonitaTaskManager(serverbonita,username,password);
			
			List<PortletBonitaLink>  list = bManager.getHumanAvailableTask((username));
			Iterator<PortletBonitaLink> iter = list.iterator();
			
			while(iter.hasNext()){
				PortletBonitaLink  link = iter.next();
				System.out.println(link.toString());
			}
			
			
		
			//assertNotNull("getTicketGrantingTicket en erreur", tasks);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}

	}

	@Test
		public void testBonitaManagerProcessusStarted() throws UserNotFoundException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, LoginException, IOException, TechnicalException {
			try{
			String username = "nataf1";
			String password = "bpm";
			String serverbonita = "http://localhost:8081";
			
			
			//String service = "https://portail.intranet.citepro.cite-sciences.fr/bonita/loginservice/";
			IBonitaTaskManager bManager = new BonitaTaskManager(serverbonita,username,password);
			
			List<PortletBonitaLink>  list =bManager.getProcessStatusInitiatedByUser(username);
			Iterator<PortletBonitaLink> iter = list.iterator();
			
			while(iter.hasNext()){
				PortletBonitaLink  link = iter.next();
				System.out.println(link.toString());
			}
			
			
			//assertNotNull("getTicketGrantingTicket en erreur", tasks);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}

	}
	

}
