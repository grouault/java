package fr.exagone.files.global;

import java.io.IOException;
import java.util.Properties;


/**
 * @author Rouault1
 * Exemple de classe permettant de loader un fichier de Props se trouvant 
 * dans le ClassPath
 * 
 * http://javarevisited.blogspot.fr/2014/07/how-to-load-resources-from-classpath-in-java-example.html
 * http://docs.oracle.com/javase/7/docs/api/java/lang/Class.html#getResourceAsStream%28java.lang.String%29
 * 
 */
public class PropertiesFiles {

	private static PropertiesFiles _instance = null;
	
	private Properties props = null;
	private static final String PROPS_FILE = "/test.properties";
	
	
	private PropertiesFiles() {
		try {
			props = loadProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized PropertiesFiles getInstance() {
		if ( _instance == null ) {
			_instance = new PropertiesFiles();
		}
		return _instance;
	}
	
	private Properties loadProperties () throws IOException {
		props = new Properties();
		props.load(getClass().getResourceAsStream(PROPS_FILE));
		return props;
	}
	
	public String getProperty(final String key) {
		return props.getProperty(key);
	}
	
	public boolean containsKey(final String key) {
		return props.containsKey(key);
	}
	
}
