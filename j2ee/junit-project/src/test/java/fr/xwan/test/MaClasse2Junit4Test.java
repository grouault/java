package fr.xwan.test;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MaClasse2Junit4Test {

	private MaClasse2 mc2;
	private static Logger log = Logger.getLogger(MaClasse2Test.class.getName());
	
	@Before
	public void initialiser() throws Exception {
		mc2 = new MaClasse2(1, 1);	
		log.info("Passage dans initialiser()");
	}
	
	@After
	public void nettoyer() throws Exception {
		mc2 = null;
		log.info("Passage dans nettoyer()");
	}
	
	@Test
	public void maClasse2() {
		Assert.assertNotNull("L'instance est créée", mc2);
		log.info("[Test] maClasse2.");
	}
	
	@Test
	public void testCalculer() throws Exception{
		Assert.assertEquals("TestCalculer - error", 2, mc2.calculer());
		log.info("[Test] testCalculer");
	}	
	
}	
