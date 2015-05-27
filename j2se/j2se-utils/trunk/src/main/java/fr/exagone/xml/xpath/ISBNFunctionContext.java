package fr.exagone.xml.xpath;

import javax.xml.namespace.QName;
import javax.xml.xpath.*;

public class ISBNFunctionContext implements XPathFunctionResolver {

  private static final QName name 
   = new QName("http://www.example.com/books", "valid-isbn");

  public XPathFunction resolveFunction(QName name, int arity) {
      if (name.equals(ISBNFunctionContext.name) && arity == 1) {
          return new ISBNValidator();
      }
      return null;
  }

}
