package fr.exagone.xstream.bean;

import junit.framework.Assert;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import fr.xwan.xml.xstream.bean.RendezvousMessage;
import fr.xwan.xml.xstream.bean.SingleValueCalendarConverter;

public class TestRendezVousMessage {

	@Test
	public void TestToXml() throws Exception {
		XStream xstream = new XStream();
		xstream.registerLocalConverter(RendezvousMessage.class, "created", new SingleValueCalendarConverter());
		xstream.processAnnotations(RendezvousMessage.class);
		RendezvousMessage msg = new RendezvousMessage(15, false, "test");
		String xml = xstream.toXML(msg);
		Assert.assertNotNull(xml);
	}

}
