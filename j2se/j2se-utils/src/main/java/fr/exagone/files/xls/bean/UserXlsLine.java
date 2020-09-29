package fr.exagone.files.xls.bean;

public class UserXlsLine implements XlsLine {

	private Integer lineNumber;
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String localisation;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	public Integer getLineNumber() {
		// TODO Auto-generated method stub
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
		
	}
	 
	
	
}
