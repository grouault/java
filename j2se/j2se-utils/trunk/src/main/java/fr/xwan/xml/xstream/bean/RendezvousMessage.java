package fr.xwan.xml.xstream.bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("message")
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"content"})
public class RendezvousMessage {

	@XStreamAlias("type")
	private int messageType;

	private String content;
	
	@XStreamConverter(value=BooleanConverter.class, booleans={false}, strings={"yes", "no"})
	private boolean important;

	@XStreamConverter(SingleValueCalendarConverter.class)
	private Calendar created = new GregorianCalendar();

	public RendezvousMessage(int messageType, boolean important, String content) {
		this.messageType = messageType;
		this.important = important;
		this.content = content;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}
	
	
}
