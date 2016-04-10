package com.book.chapter4.french;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdaptateurDate extends XmlAdapter<String, Date> {

	private static final String FORMAT = "yyyy-MM"; 
	
	@Override
	public Date unmarshal(final String valeur) throws Exception {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
		return simpleDateFormat.parse(valeur);
	}

	@Override
	public String marshal(final Date date) throws Exception {
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
		return simpleDateFormat.format(date);
	}
	
}
