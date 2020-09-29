package fr.exagone.jse.string;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;


public class RemoveHtml {

	private static String HTML = "<div>Sed ut perspiciatis <span>e omnis iste <a href=\"toto\">natus</a></span> error sit voluptatem accusantium</div>";
	
	public static void main(String[] args) {
		simpleRegExp();
		SwingExtractor();
	}

	private static void simpleRegExp(){
		System.out.println(HTML);
	 	String nohtml = HTML.replaceAll("\\<.*?>","");
	 	System.out.println(nohtml);		
	}
	
	
	private static void SwingExtractor(){
		StringReader reader;
		try {
			reader = new StringReader(HTML);
		    List<String> lines = extractText(reader);
		    for (String line : lines) {
		      System.out.print(line);
		    }			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<String> extractText(Reader reader) throws IOException {
		final ArrayList<String> list = new ArrayList<String>();

		ParserDelegator parserDelegator = new ParserDelegator();
		ParserCallback parserCallback = new ParserCallback() {
		  public void handleText(final char[] data, final int pos) {
		    list.add(new String(data));
		  }
		  public void handleStartTag(Tag tag, MutableAttributeSet attribute, int pos) { }
		  public void handleEndTag(Tag t, final int pos) {  }
		  public void handleSimpleTag(Tag t, MutableAttributeSet a, final int pos) { }
		  public void handleComment(final char[] data, final int pos) { }
		  public void handleError(final java.lang.String errMsg, final int pos) { }
		};
		parserDelegator.parse(reader, parserCallback, true);
		return list;
	}
	   
}
