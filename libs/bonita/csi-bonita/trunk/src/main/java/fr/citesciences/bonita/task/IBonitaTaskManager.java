package fr.citesciences.bonita.task;

import java.util.List;

import fr.citesciences.bonita.bean.PortletBonitaLink;
import fr.citesciences.bonita.exceptions.TechnicalException;

public interface IBonitaTaskManager {
	
	List<PortletBonitaLink> getProcessStatusInitiatedByUser(final String userName) throws TechnicalException;

	List<PortletBonitaLink> getProcessesAvailables(final String user) throws TechnicalException ;
	
	List<PortletBonitaLink> getHumanAvailableTask(final String user) throws TechnicalException ;
}
