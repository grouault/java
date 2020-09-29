package fr.exagone.xml.xpath;

import java.util.List;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class ISBNValidator implements XPathFunction {

  // This class could easily be implemented as a Singleton.
    
  public Object evaluate(List args) throws XPathFunctionException {

    if (args.size() != 1) {
      throw new XPathFunctionException("Wrong number of arguments to valid-isbn()");
    }

    String isbn;
    Object o = args.get(0);

    // perform conversions
    if (o instanceof String) isbn = (String) args.get(0);
    else if (o instanceof Boolean) isbn = o.toString();
    else if (o instanceof Double) isbn = o.toString();
    else if (o instanceof NodeList) {
        NodeList list = (NodeList) o;
        Node node = list.item(0);
        // getTextContent is available in Java 5 and DOM 3.
        // In Java 1.4 and DOM 2, you'd need to recursively 
        // accumulate the content.
        isbn= node.getTextContent();
    }
    else {
        throw new XPathFunctionException("Could not convert argument type");
    }

    char[] data = isbn.toCharArray();
    if (data.length != 10) return Boolean.FALSE;
    int checksum = 0;
    for (int i = 0; i < 9; i++) {
        checksum += (i+1) * (data[i]-'0');
    }
    int checkdigit = checksum % 11;

    if (checkdigit + '0' == data[9] || (data[9] == 'X' && checkdigit == 10)) {
        return Boolean.TRUE;
    }
    return Boolean.FALSE;

  }

}