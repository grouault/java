package fr.citesciences.bonita.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PortletBonitaLink {
	
	private String label;
	private String url;
	private String description;
	private List<String> category;
	private String state; 
	private Date dateCreated;
	private Date dateLastUpdated;
	private String startedBy;
	private String assignedTo;
	private List<BonitaActivity> activities = new ArrayList<BonitaActivity>();

	
	
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStartedBy() {
		return startedBy;
	}
	public void setStartedBy(String startedBy) {
		this.startedBy = startedBy;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}
	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
		
	}
	
	
	public List<BonitaActivity> getActivities() {
		return activities;
	}
	public void setActivities(List<BonitaActivity> activities) {
		this.activities = activities;
	}
	@Override
	public String toString() {
		return "PortletBonitaLink [label=" + label + ", url=" + url
				+ ", description=" + description + ", category=" + category
				+ ", state=" + state + ", dateCreated=" + dateCreated
				+ ", dateLastUpdated=" + dateLastUpdated + ", startedBy="
				+ startedBy + ", assignedTo=" + assignedTo + "]";
	}
	
	
	
}
