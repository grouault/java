package fr.exagone.files.global;

import org.junit.Assert;
import org.junit.Test;

public class PropertiesFileTest {

	@Test
	public void testGetProperty(){
		boolean isProperty = PropertiesFiles.getInstance().containsKey("myname");
		Assert.assertTrue(isProperty);
	}
	
}
