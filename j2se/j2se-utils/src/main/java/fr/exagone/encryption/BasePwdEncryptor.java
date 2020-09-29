package fr.exagone.encryption;

import org.apache.commons.lang.StringUtils;

import fr.exagone.jse.exception.PwdEncryptorException;

public abstract class BasePwdEncryptor implements PasswordEncryptor {

	protected abstract String doEncrypt(String plainTextPassword) throws PwdEncryptorException;
	
	protected abstract boolean doCheckedPassword(String plainTextPassword, String encryptedPassword);
	
	@Override
	public String encrypt(String plainTextPassword) throws PwdEncryptorException {
		if (StringUtils.isEmpty(plainTextPassword)) {
			throw new PwdEncryptorException("Impossible d'encrypter un mot de passe non renseigné.");
		}
		return doEncrypt(plainTextPassword);
	}

	@Override
	public boolean checkPassword(String plainTextPassword, String encryptedPassword) throws PwdEncryptorException {
		if (StringUtils.isEmpty(plainTextPassword)) {
			throw new PwdEncryptorException("Impossible d'encrypter un mot de passe non renseigné.");
		}
		if (StringUtils.isEmpty(encryptedPassword)) {
			throw new PwdEncryptorException("Impossible de checher un mot de passe encrypté non renseigné.");
		}
		return doCheckedPassword(plainTextPassword, encryptedPassword);
	}

}
