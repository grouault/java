package fr.exagone.json.jackson;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class TestJackson {

	@Test	
	public void UserToJSon()  throws Exception {
		User user = new User();
		user.setMessages(new ArrayList<String>() {
			{
				add("msg 1");
				add("msg 2");
				add("msg 3");
			}
		});
		user.setAge(36);
		user.setName("gilou");
		user.setAdresse(new Adress("1 rue jackson", "Montauban", 35360));
		String userJson = user.toJSon();
		System.out.println(userJson);
		Assert.assertNotNull(userJson);
	}
	
	@Test
	public void UserFromJSon() throws Exception {
		String userJsonValue = "{\"name\":\"gilou\",\"age\":29,\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]}";	
		User user = User.fromJSon(userJsonValue);
		Assert.assertNotNull(user);
	}
	
}
