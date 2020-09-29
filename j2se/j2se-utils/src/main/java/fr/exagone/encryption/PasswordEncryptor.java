package fr.exagone.encryption;

import fr.exagone.jse.exception.PwdEncryptorException;

public interface PasswordEncryptor {

	/**
	 * permet d'encrypter le mot passe
	 * @param plainTextPassword
	 * @param encryptedPassword
	 * @return encrypt(plainTextPassword)
	 * @throws PwdEncryptorException
	 */
	public String encrypt(final String plainTextPassword) throws PwdEncryptorException;
	
	/**
	 * checked que l'encryptage de 'plainTextPassword' est identique à 'encryptedPassword'
	 * @param plainTextPassword
	 * @param encryptedPassword
	 * @return : true si encrypt(plainTextPassword) = encryptedPassword
	 * @throws PwdEncryptorException
	 */
	public boolean checkPassword(String plainTextPassword, String encryptedPassword) throws PwdEncryptorException;
	
}
