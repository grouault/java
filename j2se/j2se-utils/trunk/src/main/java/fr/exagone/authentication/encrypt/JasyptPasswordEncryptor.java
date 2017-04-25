package fr.exagone.authentication.encrypt;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class JasyptPasswordEncryptor extends BasePasswordEncryptor {

	private StrongPasswordEncryptor spe = null;
	
	public JasyptPasswordEncryptor() {
		 spe = new StrongPasswordEncryptor();
	}
	
	@Override
	protected String doEncrypt(String plainTextPassword) {
		return spe.encryptPassword(plainTextPassword);
	}

	@Override
	protected boolean doCheckedPassword(String plainTextPassword, String encryptedPassword) {
		return spe.checkPassword(plainTextPassword, encryptedPassword);
	}

}
