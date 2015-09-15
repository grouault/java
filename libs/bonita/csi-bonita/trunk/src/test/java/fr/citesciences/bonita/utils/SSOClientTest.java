package fr.citesciences.bonita.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import fr.citesciences.bonita.exceptions.TechnicalException;

public class SSOClientTest {
	
	private static Logger logger = Logger.getLogger(SSOClientTest.class);

	@Before
	public void setUp() throws Exception {
		System.setProperty("javax.net.ssl.trustStore","C:\\partition D\\tools\\bonita\\ssl\\intranet.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "intranet");
	}

	@Test
	public void testTicketGrantingTicket() {
		
		String ticketGrantingTicket = null;
		String username = "directeur1";
		String password = "Bpm12345";
		String server = "https://portail.intranet.citepro.cite-sciences.fr/cas-server/v1/tickets/";
		String service = "https://portail.intranet.citepro.cite-sciences.fr/bonita/";
		
		SSOClient ssoClient = new SSOClient(username, password, server, service);
		try {
			 ticketGrantingTicket = ssoClient.getTicketGrantingTicket();
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("getTicketGrantingTicket en erreur", ticketGrantingTicket);
		logger.debug("ticketGranting "+ticketGrantingTicket);
	}

	
	
	@Test
	public void testServiceTicket() {
		
		
		String username = "directeur1";
		String password = "Bpm12345";
		String server = "https://portail.intranet.citepro.cite-sciences.fr/cas-server/v1/tickets/";
		String service = "https://portail.intranet.citepro.cite-sciences.fr/bonita/loginservice";
		String serviceTicket = null;
		String ticketGrantingTicket = null;
		
		SSOClient ssoClient = new SSOClient(username, password, server, service);
		try {
			 ticketGrantingTicket = ssoClient.getTicketGrantingTicket();
			 if(ticketGrantingTicket!=null)
				 serviceTicket = ssoClient.getServiceTicket(ticketGrantingTicket);
			 
			 ssoClient.logout(ticketGrantingTicket);
			 
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertNotNull("getTicketGrantingTicket en erreur", ticketGrantingTicket);
		logger.debug("ticketGranting "+ticketGrantingTicket);
		assertNotNull("serviceTicket en erreur", serviceTicket);
		logger.debug("serviceTicket "+serviceTicket);
	}
	
	
	
}
