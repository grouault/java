package fr.citesciences.bonita.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.bpm.category.Category;
import org.bonitasoft.engine.bpm.category.CategoryCriterion;
import org.bonitasoft.engine.bpm.flownode.ActivityInstance;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoSearchDescriptor;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.bpm.process.ProcessInstanceSearchDescriptor;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.LogoutException;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.engine.util.APITypeManager;
import org.bonitasoft.utils.BonitaURLTools;

import fr.citesciences.bonita.bean.BonitaActivity;
import fr.citesciences.bonita.bean.PortletBonitaLink;
import fr.citesciences.bonita.exceptions.TechnicalException;

public class BonitaTaskManager implements  IBonitaTaskManager{
	private static String APPLICATION_NAME = "bonita"; 

	private static Logger logger = Logger.getLogger(BonitaTaskManager.class);

	private String serverBonita   = null;
	private String loginBonita	  = null;
	private String passwordBonita =  null ;

	public BonitaTaskManager(String serverBonita, String loginBonita, String passwordBonita) {
		super();

		this.serverBonita   = serverBonita;
		this.loginBonita    = loginBonita;
		this.passwordBonita = passwordBonita;
	}

	public BonitaTaskManager() {
		super();
	}

	
	
	/**
	 * login bonita
	 * @return
	 * @throws BonitaHomeNotSetException
	 * @throws ServerAPIException
	 * @throws UnknownAPITypeException
	 * @throws LoginException
	 */
	private APISession login() throws BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException, LoginException{
		Map<String, String> map = new HashMap<String, String>();

		map.put("server.url", serverBonita);
		map.put("application.name", APPLICATION_NAME);

		APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, map);
		final LoginAPI loginAPI 	= TenantAPIAccessor.getLoginAPI();
	
		return loginAPI.login(loginBonita, passwordBonita);
	}
	

/**
 * logout session  bonita
 * @param session
 * @throws SessionNotFoundException
 * @throws LogoutException
 * @throws BonitaHomeNotSetException
 * @throws ServerAPIException
 * @throws UnknownAPITypeException
 */
	
	private void logout(APISession session) throws SessionNotFoundException, LogoutException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
		
		final LoginAPI loginAPI 	= TenantAPIAccessor.getLoginAPI();
		loginAPI.logout(session);
		
	}
	
	/**
	 * checkk si les paramètre de setup sont ok
	 * @param userName
	 * @throws TechnicalException
	 */
	
	
	private void check(String userName) throws TechnicalException{
		
		if(serverBonita == null){
			throw new TechnicalException(" server name bonita must be filled ");
		}
		
		if (loginBonita == null) {
			throw new TechnicalException(" user bonita name must be filled ");
		}

		if (passwordBonita == null) {
			throw new TechnicalException(" password bonita must be filled ");
		}
		
		if(userName == null){
			throw new TechnicalException(" userName must be filled ");
		}
	}
	
	
	/**
	 * 
	 * @param userName
	 * @return
	 * @throws TechnicalException
	 */
	public List<PortletBonitaLink> getProcessStatusInitiatedByUser(final String userName) throws TechnicalException {

		
		APISession session = null;
		List<PortletBonitaLink> links = new ArrayList<PortletBonitaLink>();
		
		try{
		List<String> categoryList = new ArrayList<String>();
		check(userName);
		session = login();
		
		//loginAPI.login(credentials)
		//final Map<String, Serializable> credentials = new HashMap<String, Serializable>();
		//credentials.put(AuthenticationConstants.CAS_TICKET, ticket);
		//APISession session = loginAPI.login(credentials);

		
		
		final IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		
		User user = identityAPI.getUserByUserName(userName);
		if(user == null){
			throw new TechnicalException(" user"+ user +" not found ");			
		}
		
		
		final SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 100);
		builder.filter(ProcessInstanceSearchDescriptor.STARTED_BY,user.getId());

		
		final SearchResult<ProcessInstance> processInstanceResults = processAPI.searchOpenProcessInstances(builder.done());
		Iterator<ProcessInstance> iter = processInstanceResults.getResult().iterator();
		
		while (iter.hasNext()) {
			List<Category>categories  = null;
			PortletBonitaLink ptasks = new PortletBonitaLink();
			ProcessInstance processInstance = (ProcessInstance)iter.next();
			if(logger.isDebugEnabled()){
				logger.debug("task:[" + processInstance.getName() + "] description :["
						+ processInstance.getDescription() + "]");
				
			}
			
			categories = processAPI.getCategoriesOfProcessDefinition(processInstance.getProcessDefinitionId(), 0, 5,  CategoryCriterion.NAME_DESC);
			
			if(categories != null && categories.size()!=0){
				Category category =(Category)categories.get(0);
				categoryList.add(category.getName());			
			}
			
		
	
			ptasks.setLabel(processInstance.getName());
			ptasks.setUrl(BonitaURLTools.getOverviewCaseURL(serverBonita, processInstance.getId(), processAPI));
			ptasks.setDescription(processInstance.getDescription());
			ptasks.setState(processInstance.getState());
			ptasks.setDateCreated(processInstance.getStartDate());
			ptasks.setDateLastUpdated(processInstance.getLastUpdate());
			ptasks.setStartedBy(user.getFirstName()+" "+ user.getLastName());
			
			List<ActivityInstance> activities = processAPI.getOpenActivityInstances(processInstance.getId(), 0, 5, null);
			Iterator<ActivityInstance> iteratorActivity =activities.iterator();
			while(iteratorActivity.hasNext()){
				BonitaActivity bonitaActivity = new BonitaActivity();
				ActivityInstance activity = iteratorActivity.next();
				if( activity.getExecutedBy()!=0){
					User assigned  = identityAPI.getUser(activity.getExecutedBy());
					bonitaActivity.setExecutedby(assigned.getLastName() +" "+assigned.getLastName());
				}
				bonitaActivity.setExecutedDate(activity.getLastUpdateDate());
				bonitaActivity.setName(activity.getName());
				bonitaActivity.setState(activity.getState());
				ptasks.getActivities().add(bonitaActivity);
			}
	
			
			links.add(ptasks);

		}

		
		}
		catch(Exception ex){
			throw new  TechnicalException(ex.getMessage());
		}finally{
			try {
				logout(session);
			} catch (SessionNotFoundException | LogoutException
					| BonitaHomeNotSetException | ServerAPIException
					| UnknownAPITypeException e) {
				// TODO Auto-generated catch block
				throw new TechnicalException(e.getMessage());
			}
		}
		return links;
	}

	
	

	/**
	 * Liste des processus bonita disponibles pour un salarié donnée en input
	 * @param user
	 * @return
	 * @throws TechnicalException
	 */
	public List<PortletBonitaLink> getProcessesAvailables(final String user) throws TechnicalException {
		
		List<PortletBonitaLink> links = new ArrayList<PortletBonitaLink>();
		APISession session = null;
		List<String> categoryList = new ArrayList<String>();
		try {
		check(user);
		session= login();
		final IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		
		User bonitaUser = identityAPI.getUserByUserName(user);
		if(bonitaUser == null){
			throw new TechnicalException(" user"+ user +" not found ");			
		}

		final SearchOptions searchOptions = new SearchOptionsBuilder(0, 100).sort(ProcessDeploymentInfoSearchDescriptor.DISPLAY_NAME, Order.DESC).done();
		final SearchResult<ProcessDeploymentInfo> processesInfo = processAPI.searchProcessDeploymentInfosCanBeStartedBy(bonitaUser.getId(), searchOptions);
		
		
		Iterator<ProcessDeploymentInfo> iter = processesInfo.getResult().iterator();
		
		while (iter.hasNext()) {
			List<Category>categories  = null;
			long processDefinitionId = -1;
			
			PortletBonitaLink ptasks = new PortletBonitaLink();
		
			ProcessDeploymentInfo processDeploymentInfo = iter.next();
			processDefinitionId = processAPI.getProcessDefinitionId(processDeploymentInfo.getName(), processDeploymentInfo.getVersion());
			
			ptasks.setLabel(processDeploymentInfo.getDisplayName());
			ptasks.setUrl(BonitaURLTools.getStartProcessCaseURL(serverBonita, processDefinitionId, processAPI));
			ptasks.setDescription(processDeploymentInfo.getDescription());
			
			categories = processAPI.getCategoriesOfProcessDefinition(processDefinitionId, 0, 5,  CategoryCriterion.NAME_DESC);
			
			if(categories != null && categories.size()!=0){
				Category category =(Category)categories.get(0);
				categoryList.add(category.getName());			
			}
			ptasks.setCategory(categoryList);
			// processsus enabled ou disabled
			ptasks.setState(processDeploymentInfo.getActivationState().name());
			links.add(ptasks);
		}
		}catch(Exception ex){
			throw new TechnicalException(ex.getMessage());
		}finally{
			try {
				logout(session);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				throw new TechnicalException(ex.getMessage());
			
		}
			}

		return links;
		
	}
	
	/**
	 * Liste des tâches que l'utilisateur passé en input peut executer
	 * @param user
	 * @return
	 * @throws TechnicalException
	 */
	public List<PortletBonitaLink> getHumanAvailableTask(final String user) throws TechnicalException {

		List<PortletBonitaLink> links = new ArrayList<PortletBonitaLink>();
		APISession session = null;
		List<String> categoryList = new ArrayList<String>();
		
		try {
		check(user);
		session = login();
		
		final IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(session);
		
		User bonitaUser = identityAPI.getUserByUserName(user);
		if(bonitaUser == null){
			throw new TechnicalException(" user"+ user +" not found ");			
		}
		
	
		final SearchOptions searchOptions = new SearchOptionsBuilder(0, 100).sort(ProcessDeploymentInfoSearchDescriptor.DISPLAY_NAME, Order.DESC).done();
		SearchResult<HumanTaskInstance> result= processAPI.searchMyAvailableHumanTasks(bonitaUser.getId(), searchOptions);
		
		Iterator<HumanTaskInstance> iter = result.getResult().iterator();
		
		while (iter.hasNext()) {
			List<Category>categories  = null;
			long processDefinitionId = -1;

			
			PortletBonitaLink ptasks = new PortletBonitaLink();
			HumanTaskInstance task = (HumanTaskInstance)iter.next();
	
			ptasks.setLabel(task.getDisplayName());
			ptasks.setUrl(BonitaURLTools.getHumanTaskURL(serverBonita, task.getId(), processAPI));
			ptasks.setDescription(task.getDescription());
			ptasks.setState(task.getState());
			ptasks.setDateLastUpdated(task.getLastUpdateDate());
			// Categories
			categories = processAPI.getCategoriesOfProcessDefinition(processDefinitionId, 0, 5,  CategoryCriterion.NAME_DESC);
			if(categories != null && categories.size()!=0){
				Category category =(Category)categories.get(0);
				categoryList.add(category.getName());			
			}
			ptasks.setCategory(categoryList);
			
	
			ProcessInstance processInstance=  processAPI.getProcessInstance(task.getParentProcessInstanceId());
			
			ptasks.setDateCreated(processInstance.getStartDate());
			
			Long initiatorId = processInstance.getStartedBy();
			User initiator = identityAPI.getUser(initiatorId);
			
			ptasks.setStartedBy(initiator.getFirstName()+" "+ initiator.getLastName());
			
			// si la tache est assignée
			if(task.getAssigneeId()!= 0){
				User assigned  = identityAPI.getUser(task.getAssigneeId());
				ptasks.setAssignedTo(assigned.getFirstName()+" "+ assigned.getLastName());
			}
			
			links.add(ptasks);
		}

		}catch(Exception ex){
			throw new TechnicalException(ex.getMessage());
		}finally{
			try {
				logout(session);
			} catch (Exception ex) {
				
				throw new TechnicalException(ex.getMessage());
			
		}
			}

		return links;
		
	}
}
