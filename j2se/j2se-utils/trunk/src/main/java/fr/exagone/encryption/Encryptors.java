package fr.exagone.encryption;

public enum Encryptors {

	JASYPT("jasypt");

	private String name;

	private Encryptors(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
