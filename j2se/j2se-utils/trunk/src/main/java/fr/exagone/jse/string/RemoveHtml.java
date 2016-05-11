package fr.exagone.jse.string;


public class RemoveHtml {

	private static String HTML = "<div>Sed ut perspiciatis <span>e omnis iste <a href=\"toto\">natus</a></span> error sit voluptatem accusantium</div>";
	
	   public static void main (String[] args) throws Exception{
	   	 System.out.println(HTML);
	     String nohtml = HTML.replaceAll("\\<.*?>","");
	     System.out.println(nohtml);
	   }
	
}
