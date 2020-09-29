package fr.exagone.xstream.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import fr.xwan.xml.xstream.bean.MessageSession;

public class TestMessageSession {

	private static final String PATH_FILE = "D:/devs/workspaces/helios/content-server/files-project/files/xstream/";
	
	@Test
	public void TestToXml() throws Exception {
		// xstream
		XStream xstream = new XStream();
		xstream.processAnnotations(MessageSession.class);
		
		// java --> xml
		MessageSession msg = new MessageSession();
		msg.setTemps("20");		
		msg.setTexte("texte");
		String xml = xstream.toXML(msg);
		Assert.assertNotNull(xml);
		
	}
	
	@Test
	public void TestFromXml() throws Exception {
		// xstream
		XStream xstream = new XStream();
		xstream.processAnnotations(MessageSession.class);
		StringBuffer buffer = new StringBuffer();
		String line;
		
		File file = new File(PATH_FILE + "message.xml");
		InputStream  in = new FileInputStream(file);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		while ((line = bfr.readLine()) != null) {
			buffer.append(line);
		}
		String xml = buffer.toString();
		MessageSession message = (MessageSession) xstream.fromXML(xml);
		Assert.assertNotNull(message);
		
	}
	
}
