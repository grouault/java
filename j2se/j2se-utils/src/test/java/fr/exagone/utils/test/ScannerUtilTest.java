package fr.exagone.utils.test;

import junit.framework.Assert;

import org.junit.Test;

import fr.exagone.utils.ScannerUtil;

public class ScannerUtilTest {

	@Test
	public void parseCsvTest () {
		Assert.assertNotNull(ScannerUtil.parseCsvFile());
	}
	
}
