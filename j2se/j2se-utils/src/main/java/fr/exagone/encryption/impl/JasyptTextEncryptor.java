package fr.exagone.encryption.impl;

import org.jasypt.util.text.StrongTextEncryptor;

public class JasyptTextEncryptor {

	private StrongTextEncryptor ste = null;
	
	
	public static void main(String[] args) {
		
		JasyptTextEncryptor textEncryptor = new JasyptTextEncryptor();
		String password = "opconn_1234";
		String encryptPassword = textEncryptor.encrypt(password);
		String decryptPassword = textEncryptor.decrypt(encryptPassword);
		
		System.out.println("password = " + password); 
		System.out.println("encryptedPassword = " + encryptPassword);
		System.out.println("decryptPassword = " + decryptPassword);
		
	}
	
	public JasyptTextEncryptor() {
		this.ste = new StrongTextEncryptor();
	}
	
	public String encrypt(String password) {
		return this.ste.encrypt(password);
	} 
	
	public String decrypt(String encryptedPassword) {
		return this.ste.decrypt(encryptedPassword);
	}
	
}
