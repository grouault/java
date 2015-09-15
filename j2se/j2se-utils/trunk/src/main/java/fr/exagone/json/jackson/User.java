package fr.exagone.json.jackson;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class User {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private int age;
	private String name;
	private List<String> messages;
	private Adress adresse;
	private String url;
	
	/**
	 * convertit un objet user en flux json.
	 * @return
	 */
	public String toJSon () {
		String jsonValue = null;
		try {
			jsonValue = mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonValue;
	}
	
	/**
	 * retourne un user a partir de son flux JSon.
	 * @param userJsonValue
	 * @return
	 */
	public static User fromJSon(final String userJsonValue) {
		User user = null;
		try {
			user = mapper.readValue(userJsonValue, User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
 
	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", " +
				"messages=" + messages + "]";
	}
	
	// Metier specifique.
	public String getUrl() {
		return new String("http:\\test.fr\\" + getName());
	}
	
	//getter and setter methods
	public void setUrl(String url) {
		this.url = url;
	}	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Adress getAdresse() {
		return adresse;
	}

	public void setAdresse(Adress adresse) {
		this.adresse = adresse;
	}
	
}
