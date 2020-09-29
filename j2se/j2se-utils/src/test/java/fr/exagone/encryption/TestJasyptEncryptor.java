package fr.exagone.encryption;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import fr.exagone.jse.exception.PwdEncryptorException;
import junit.framework.Assert;

public class TestJasyptEncryptor {

	private EncryptorsFactory encryptorsFactory = null;
	private static Logger _logger = Logger.getLogger(TestJasyptEncryptor.class.getName());
	
	@Before
	public void initialiser() throws Exception {
		encryptorsFactory = new EncryptorsFactory();	
		_logger.info("Passage dans initialiser()");
	}
	
	@Test
	public void encrypt() throws PwdEncryptorException {
		String toEncrypt = "gildaspwd";
		PasswordEncryptor encryptor = encryptorsFactory.createEncryptor(Encryptors.JASYPT);
		String encryptedValue = encryptor.encrypt(toEncrypt);
		_logger.debug("toEncrypt = " + toEncrypt + " / encryptedValue = " + encryptedValue);
		Assert.assertNotSame(toEncrypt, encryptedValue);
	}
	
}
