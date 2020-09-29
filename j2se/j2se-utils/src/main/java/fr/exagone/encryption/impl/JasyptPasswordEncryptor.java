package fr.exagone.encryption.impl;

import org.jasypt.util.password.StrongPasswordEncryptor;

import fr.exagone.encryption.BasePwdEncryptor;
import fr.exagone.jse.exception.PwdEncryptorException;

public class JasyptPasswordEncryptor extends BasePwdEncryptor {

	private StrongPasswordEncryptor spe = null;
	
	public JasyptPasswordEncryptor() {
		spe = new StrongPasswordEncryptor();
	}
	
	@Override
	protected String doEncrypt(String plainTextPassword) throws PwdEncryptorException {
		return spe.encryptPassword(plainTextPassword);
	}

	@Override
	protected boolean doCheckedPassword(String plainTextPassword, String encryptedPassword) {
		return spe.checkPassword(plainTextPassword, encryptedPassword);
	}

}
