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

import fr.xwan.xml.xstream.bean.Message;

public class TestMessage {

	private static final String PATH_FILE = "/home/rouault/informatique/workspaces/indigo/J2SE/langage/files-project/files/xstream/";
	
	@Test
	public void TestToXml() throws Exception {
		// xstream
		XStream xstream = new XStream();
		xstream.processAnnotations(Message.class);
		
		// java --> xml
		Message msg = new Message();
		msg.setTemps("20");		
		msg.setTexte("texte");
		String xml = xstream.toXML(msg);
		Assert.assertNotNull(xml);
		
	}
	
	@Test
	public void TestFromXml() throws Exception {
		// xstream
		XStream xstream = new XStream();
		xstream.processAnnotations(Message.class);
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
		Message message = (Message) xstream.fromXML(xml);
		Assert.assertNotNull(message);
		
	}
	
}
