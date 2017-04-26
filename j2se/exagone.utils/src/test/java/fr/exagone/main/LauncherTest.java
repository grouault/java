package fr.exagone.main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.exagone.sgbd.Connexion;
import junit.framework.Assert;

public class LauncherTest {

	private Connexion connexion = null;
	private SgbdLauncher launcher = null;
	
	@Before
	public void init() {
		connexion = new Connexion();
		launcher = new SgbdLauncher();
	}
	
	@After
	public void end() {
		connexion.close();
	}
	
	@Test
	public void testLaunch() {
		launcher.launch(false);
		Assert.assertTrue(true);
	}
}
