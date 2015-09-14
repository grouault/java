package tudu.web.bean;

public class RestoreData {
    private String restoreChoice;
    private String listId;
    private byte[] backupFile;

    public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public String getRestoreChoice() {
		return restoreChoice;
	}
	public void setRestoreChoice(String restoreChoice) {
		this.restoreChoice = restoreChoice;
	}
	public byte[] getBackupFile() {
		return backupFile;
	}
	public void setBackupFile(byte[] backupFile) {
		this.backupFile = backupFile;
	}
}