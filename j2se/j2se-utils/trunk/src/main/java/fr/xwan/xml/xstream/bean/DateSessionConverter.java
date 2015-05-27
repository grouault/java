package fr.xwan.xml.xstream.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DateSessionConverter implements Converter {

	private DateFormat formatDate;
	
	public DateSessionConverter() {
		this.formatDate = new SimpleDateFormat("yyyyMMdd");
	}
	
	public DateSessionConverter(final SimpleDateFormat simpleDateFormat) {
		this.formatDate = simpleDateFormat;
	}
	
	public boolean canConvert(Class type) {
		return type.equals(Date.class);
	}

	/**
	 * transforme la date en valeur xml.
	 */
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Date sessionDate = (Date) source;
		writer.setValue(formatDate.format(sessionDate));
	}

	/**
	 * transforme la valeur du flux xml en Date.
	 */
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		Date sessionDate = null;
		String strDate = reader.getValue();
		if (strDate != null && !"".equals(strDate)) {
			try {
				sessionDate = formatDate.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return sessionDate;
	}

}
