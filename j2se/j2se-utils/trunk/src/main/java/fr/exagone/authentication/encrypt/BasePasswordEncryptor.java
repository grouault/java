package fr.exagone.authentication.encrypt;

import org.apache.commons.lang.StringUtils;

/**
 * @author grouault
 */
public abstract class BasePasswordEncryptor implements PasswordEncryptor {
	
	protected abstract String doEncrypt(String plainTextPassword);
	
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
