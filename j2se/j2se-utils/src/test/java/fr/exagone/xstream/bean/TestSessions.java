package fr.exagone.xstream.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import junit.framework.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import fr.xwan.xml.xstream.bean.Sessions;

public class TestSessions {
	
	private static final String PATH_FILE = "/home/rouault/informatique/workspaces/indigo/J2SE/langage/files-project/files/xstream/";
	
	@Test
	public void TestFromXml() throws Exception {
		// xstream
		// XStream xstream = new XStream(new DomDriver());
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
		xstream.processAnnotations(Sessions.class);
		
		StringBuffer buffer = new StringBuffer();
		String line;
		
		File file = new File(PATH_FILE + "sessions_bsi_formations.xml");
		// File file = new File(PATH_FILE + "sessions.xml");
		// File file = new File(PATH_FILE + "sessions-test.xml");
		InputStream  in = new FileInputStream(file);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		while ((line = bfr.readLine()) != null) {
			buffer.append(line);
		}
		String xml = buffer.toString();
		Sessions sessions = (Sessions) xstream.fromXML(xml);
		Assert.assertNotNull(sessions);
		
	}
}
