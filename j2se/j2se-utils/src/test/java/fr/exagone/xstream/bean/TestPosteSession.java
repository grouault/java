package fr.exagone.xstream.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import fr.xwan.xml.xstream.bean.DateSessionConverter;
import fr.xwan.xml.xstream.bean.PosteSession;
import fr.xwan.xml.xstream.bean.PosteSessionConverter;

/**
 * notes :
 * 	http://stackoverflow.com/questions/9333035/xstream-and-underscores
 * @author rouault
 *
 */
public class TestPosteSession {

	private static final String PATH_FILE = "D:/devs/workspaces/helios/content-server/files-project/files/xstream/";
	
	/*
	@Test
	public void TestFromXml() throws Exception {
		// xstream
		// XStream xstream = new XStream();
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
		// xstream.registerLocalConverter(PosteSession.class, "dateDebut", new DateSessionConverter());
		xstream.processAnnotations(PosteSession.class);
		
		StringBuffer buffer = new StringBuffer();
		String line;
		
		File file = new File(PATH_FILE + "poste-sessions.xml");
		InputStream  in = new FileInputStream(file);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		while ((line = bfr.readLine()) != null) {
			buffer.append(line);
		}
		String xml = buffer.toString();
		PosteSession posteSession = (PosteSession) xstream.fromXML(xml);
		Assert.assertNotNull(posteSession);
		
	}
	*/
	
	@Test
	public void TestToXml() throws Exception {
		Calendar calendar = Calendar.getInstance();
		PosteSession posteSession = new PosteSession();
		posteSession.setDateDebut(calendar);
		posteSession.setDateFin(calendar);
		posteSession.setHeureDebut(calendar);
		posteSession.setHeureFin(calendar);
		posteSession.setJours(null);
		
		XStream xstream = new XStream();
		xstream.processAnnotations(PosteSession.class);
		String xml = xstream.toXML(posteSession);
		Assert.assertNotNull(xml);
	}
	
	@Test
	public void TestFromXml() throws Exception {
		// xstream
		XStream xstream = new XStream(new DomDriver());
		// xstream.registerConverter(new PosteSessionConverter());
		xstream.processAnnotations(PosteSession.class);
		
		StringBuffer buffer = new StringBuffer();
		String line;
		
		File file = new File(PATH_FILE + "poste-sessions.xml");
		InputStream  in = new FileInputStream(file);
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		while ((line = bfr.readLine()) != null) {
			buffer.append(line);
		}
		String xml = buffer.toString();
		PosteSession posteSession = (PosteSession) xstream.fromXML(xml);
		Assert.assertNotNull(posteSession);
		
	}	
	
}
