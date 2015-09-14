package tudu.web.bean;

public class AdministrationData {
    private String smtpHost;
    private String smtpPort;
    private String smtpUser;
    private String smtpPassword;
    private String smtpFrom;

    public String getSmtpFrom() {
		return smtpFrom;
	}
	public void setSmtpFrom(String smtpFrom) {
		this.smtpFrom = smtpFrom;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPassword() {
		return smtpPassword;
	}
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpUser() {
		return smtpUser;
	}
	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

}
