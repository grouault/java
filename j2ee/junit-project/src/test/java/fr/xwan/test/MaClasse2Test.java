package fr.xwan.test;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

public class MaClasse2Test extends TestCase {
	
	private MaClasse2 mc2;
	private static Logger log = Logger.getLogger(MaClasse2Test.class.getName());
	
	protected void setUp() throws Exception{
		super.setUp();
		mc2 = new MaClasse2(1, 1);
		log.info("Appel de la methode SetUp.");
	}
	
	public void tearDown() throws Exception{
		super.tearDown();
		mc2 = null;
		log.info("Appel de la methode TearDown.");
	}	
	
	public void testCalculer() throws Exception{
		assertEquals("TestCalculer - error", 2, mc2.calculer());
		log.debug("[TestCalculer].");
	}
	
	public void testSommer() throws Exception{
		log.debug("[TestSommer].");
		// cas de test1.
		assertEquals(2, mc2.sommer());
		
		//cas de test2 : Ce test declenche une exception.
		try {
			mc2.setA(0);
			mc2.setB(0);
			mc2.sommer();
			// à ce stade l'exception est levée, sinon on fait echouer le test.
			fail("Une exception de type IllegalStateException aurait du etre levee");
		} catch (IllegalStateException ise){	
		}
	}
	
	public static Test suite() {
		TestSetup setup = new TestSetup(new TestSuite(MaClasse2Test.class)){
		      protected void setUp() throws Exception {
		          // code execute une seule fois avant l'exécution des cas de tests
		          System.out.println("Appel de la methode setUp() de la classe de tests");
		      }
		      protected void tearDown() throws Exception {
		          // code execute une seule fois après l'exécution de tous les cas de tests
		    	  System.out.println("Appel de la methode tearDown() de la classe de tests");
		      }
		};
		return setup;
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
			
}
