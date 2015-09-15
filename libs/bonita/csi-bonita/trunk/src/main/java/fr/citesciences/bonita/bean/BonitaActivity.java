package fr.citesciences.bonita.bean;

import java.util.Date;



public class BonitaActivity {
	private String name;
	private String executedby;
	private Date executedDate;
	private String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExecutedby() {
		return executedby;
	}
	public void setExecutedby(String executedby) {
		this.executedby = executedby;
	}
	
	public Date getExecutedDate() {
		return executedDate;
	}
	public void setExecutedDate(Date executedDate) {
		this.executedDate = executedDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BonitaActivities [name=" + name + ", executedby=" + executedby
				+ ", executedDate=" + executedDate.toString() + ", state=" + state + "]";
	}
		


}
