package fr.exagone.authentication.encrypt;

public interface PasswordEncryptor {

	public String encrypt(String plainTextPassword) throws PwdEncryptorException;
	
	public boolean checkPassword(String plainTextPassword, String encryptedPassword) throws PwdEncryptorException;
	
}
