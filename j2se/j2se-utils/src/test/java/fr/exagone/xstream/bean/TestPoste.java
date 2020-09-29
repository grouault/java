package fr.exagone.xstream.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import fr.xwan.xml.xstream.bean.Poste;

public class TestPoste {

	private static final String PATH_FILE = "D:/devs/workspaces/helios/content-server/files-project/files/xstream/";
	
	@Test
	public void TestFromXml() throws Exception {
		// xstream
		// XStream xstream = new XStream(new DomDriver());
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
		xstream.processAnnotations(Poste.class);
		
		StringBuffer buffer = new StringBuffer();
		String line;
		
		File file = new File(PATH_FILE + "poste.xml");
		InputStream  in = new FileInputStream(file);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		while ((line = bfr.readLine()) != null) {
			buffer.append(line);
		}
		String xml = buffer.toString();
		Poste poste = (Poste) xstream.fromXML(xml);
		Assert.assertNotNull(poste);
		
	}
}
