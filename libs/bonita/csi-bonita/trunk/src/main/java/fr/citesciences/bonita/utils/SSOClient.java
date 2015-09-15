package fr.citesciences.bonita.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import fr.citesciences.bonita.exceptions.TechnicalException;




/**
 * @author Nataf1
 *
 */
public class SSOClient {
	private static Logger logger = Logger.getLogger(SSOClient.class);

	private HttpClient client = new HttpClient();
	private  String username = null;
	private  String password = null;
	private  String server 	 = null;
	private  String service  = null; 
	
	
	
	
	public SSOClient(String username, String password, String server, String service) {
		super();
		this.service	= service;
		this.username 	= username;
		this.password 	= password;
		this.server 	= server;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	/**	
	Request for a Ticket Granting Ticket Resource
	POST /cas/v1/tickets HTTP/1.0
	 
	username=battags&password=password&additionalParam1=paramvalue
	Response for a Ticket Granting Ticket Resource
	Successful Response
	201 Created
	Location: http://www.whatever.com/cas/v1/tickets/{TGT id}
	**/	

	public String getTicketGrantingTicket() throws TechnicalException {
		if(server == null){
			throw new TechnicalException("SSO Server URL must be filled ");
		}
		
		if(username ==null){
			throw new TechnicalException("SSO user name must be filled ");
		}
		
		if(password ==null){
			throw new TechnicalException("SSO password must be filled ");
		}
		
		
		PostMethod post = new PostMethod(server);
		ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
		arr.add(new NameValuePair("username", username));
		arr.add(new NameValuePair("password", password));
		post.setRequestBody(arr.toArray(new NameValuePair[2]));
		
		try {
			client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			logger.debug("Response: " + response);
			switch (post.getStatusCode()) {
			case 201:
				Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*")
						.matcher(response);
				if (matcher.matches()) {
					return matcher.group(1);
				}
				logger.debug("Successful ticket granting request, but no ticket found!");
				logger.debug("Response (1k): "
						+ response.substring(0,
								Math.min(1024, response.length())));
				break;
			default:
				logger.debug("Invalid response code ("
						+ post.getStatusCode() + ") from CAS server!");
				logger.debug("Response: " + response);
				break;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			post.releaseConnection();
		}
		return null;
	}
	
	
	/**
	Request for a Service Ticket
	POST /cas/v1/tickets/{TGT id} HTTP/1.0
	 
	service={form encoded parameter for the service url}
	Response for Service Ticket
	Successful Response
	200 OK
	 
	ST-1-FFDFHDSJKHSDFJKSDHFJKRUEYREWUIFSD2132
	 * @throws IOException 
	**/
	public String getServiceTicket( String ticketGrantingTicket) throws TechnicalException, IOException {
		
		if (ticketGrantingTicket == null || ticketGrantingTicket.isEmpty()) {
			throw new TechnicalException("ticketGrantingTicket is null ");
		}
		
		if(server ==null){
			throw new TechnicalException("SSO Server URL must be filled ");
		}
		
		if(service ==null){
			throw new TechnicalException("SSO Service must be filled ");
		}
		
		PostMethod post = new PostMethod(server + ticketGrantingTicket);
		
		ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
		arr.add(new NameValuePair("service", service));
		arr.add(new NameValuePair("username", username));
		arr.add(new NameValuePair("password", password));
		
		post.setRequestBody(arr.toArray(new NameValuePair[3]));
		
		try {
			client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			switch (post.getStatusCode()) {
			case 200:
				return response;
			default:
				logger.warn("Invalid response code ("
						+ post.getStatusCode() + ") from CAS server!");
				logger.warn("Response (1k): "
						+ response.substring(0,
								Math.min(1024, response.length())));
				break;
			}
		} catch (final IOException e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			post.releaseConnection();
		}
		return null;
	}
	
	
	
	
	
	/**Request for a Service Ticket
	POST /cas/v1/tickets/{TGT id} HTTP/1.0
	 
	service={form encoded parameter for the service url}
	Response for Service Ticket
	Successful Response
	200 OK
	 
	ST-1-FFDFHDSJKHSDFJKSDHFJKRUEYREWUIFSD2132 
	 * @throws TechnicalException **/
	
	public void getServiceCall( String serviceTicket) throws TechnicalException {
		
		if(service ==null){
			throw new TechnicalException("SSO service must be filled ");
		}
		
		if(serviceTicket ==null){
			throw new TechnicalException("SSO serviceTicket must be filled ");
		}
		GetMethod method = new GetMethod(service+"loginservice?ticket="+serviceTicket);
		ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
		arr.add(new NameValuePair("ticket", serviceTicket));
		method.setQueryString(arr.toArray(new NameValuePair[1]));
		
		try {
			client.executeMethod(method);
			String response = method.getResponseBodyAsString();
			
			
			switch (method.getStatusCode()) {
			case 200:
				logger.debug("Response: " + response);
				break;
			default:
				logger.warn("Invalid response code ("
						+ method.getStatusCode() + ") from CAS server!");
				logger.warn("Response: " + response);
				break;
			}
		} catch (final IOException e) {
			logger.error(e.getMessage());
		} finally {
			method.releaseConnection();
		}
		
		
	}
	
public void getServiceCall( String api,String serviceTicket) throws TechnicalException {
		
		if(service ==null){
			throw new TechnicalException("SSO service must be filled ");
		}
		
		if(serviceTicket ==null){
			throw new TechnicalException("SSO serviceTicket must be filled ");
		}
		GetMethod method = new GetMethod(api+serviceTicket);
		ArrayList<NameValuePair> arr = new ArrayList<NameValuePair>();
		arr.add(new NameValuePair("ticket", serviceTicket));
		method.setQueryString(arr.toArray(new NameValuePair[1]));
		
		try {
			client.executeMethod(method);
			String response = method.getResponseBodyAsString();
			
			
			switch (method.getStatusCode()) {
			case 200:
				logger.debug("Response: " + response);
				break;
			default:
				logger.warn("Invalid response code ("
						+ method.getStatusCode() + ") from CAS server!");
				logger.warn("Response: " + response);
				break;
			}
		} catch (final IOException e) {
			logger.error(e.getMessage());
		} finally {
			method.releaseConnection();
		}
		
		
	}

	
	/**
	 * Logout of the Service
	To log out, you merely need to delete the ticket.
	DELETE /cas/v1/tickets/TGT-fdsjfsdfjkalfewrihfdhfaie HTTP/1.0
	 * @throws TechnicalException 
	**/
	public void logout(String ticketGrantingTicket) throws TechnicalException {
		
		
		if(ticketGrantingTicket ==null){
			throw new TechnicalException("SSO ticketGrantingTicket must be filled ");
		}
		
		DeleteMethod method = new DeleteMethod(server + ticketGrantingTicket);
		try {
			client.executeMethod(method);
			switch (method.getStatusCode()) {
			case 200:
				System.out.println("Logged out");
				break;
			default:
				logger.warn("Invalid response code ("
						+ method.getStatusCode() + ") from CAS server!");
				// System.out.println("Response: "+response);
				break;
			}
		} catch (final IOException e) {
			logger.error(e.getMessage());
		} finally {
			method.releaseConnection();
		}
	}

}
