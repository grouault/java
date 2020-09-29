package fr.exagone.encryption;

import fr.exagone.encryption.impl.JasyptPasswordEncryptor;

public class EncryptorsFactory {

	public PasswordEncryptor createEncryptor(Encryptors encryptor) {
		
		PasswordEncryptor toReturn = null;
		
		switch (encryptor) {
		case JASYPT:
			toReturn = new JasyptPasswordEncryptor();
			break;

		default:
			System.out.println("Erreur : aucun constructeur relatif à l'encryptor : " + encryptor);
			break;
		}
		
		return toReturn;
	}
	
}
