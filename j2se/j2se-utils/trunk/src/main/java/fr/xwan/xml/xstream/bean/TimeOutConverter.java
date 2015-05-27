package fr.xwan.xml.xstream.bean;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class TimeOutConverter implements Converter {

	public boolean canConvert(Class arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void marshal(Object arg0, HierarchicalStreamWriter arg1,
			MarshallingContext arg2) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext arg1) {
		return reader.getValue();
	}

	
}
