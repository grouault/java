package fr.exagone.test;

import junit.framework.Assert;

import org.junit.Test;

import fr.exagone.model.Personne;

public class PersonneTest {

    @Test		
	public void PersonneTest() {
		Personne personne = new Personne("titi", "toto");
		Assert.assertNotNull(personne);
	}
}
