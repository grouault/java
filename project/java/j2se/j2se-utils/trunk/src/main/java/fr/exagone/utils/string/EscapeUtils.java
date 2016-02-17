package fr.exagone.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class EscapeUtils {

	public static final String chaineTest = "#all period < 30 &test=toto";
	public static final String chaineTest2 = "NOT(affiche:0) AND date_debut>01/12/2013";
	public static final String chaineTest3 = "Le fichier kf_n368_p22-31_w.pdf";
	
	public static final String CHAINE_CONTEXT = "hf%3D10%26l%3Dfr%26of%3Dflea%26q%3DPESTe%26s%3Dtext_relevance%26sl%3Dslestim%26start%3D0%26r%3Df%252Fstructures%252Fle%2Bpavillon%2Bdes%2Bsciences%26r%3Df%252Fregion%252Fi%26r%3Df%252Fsource%252Festim_structures";
	public static final String CHAINE_CONTEXT_RESSOURCE = "hf%3D10%26l%3Dfr%26q%3Dgirafe%26s%3Dtext_relevance%26sl%3Dslestim%26start%3D0%26r%3Df%252Fsource%252Festim_bdr%26r%3Df%252Faccessibilite%252Facs_non%26r%3Df%252Ftype_doc%252Fvideo";
	
	public static final String JSON_VALUE = "{\"titi\":\"toto\"}";
	
	
	
	public static final String JSON_VALUE_TEST = "{\"timeline\":{\"headline\":\"Evenements a venir\",\"type\":\"default\",\"texte\":\"Liste des evenements a venir\",\"date\":[{\"startDate\":\"2014,10,22\",\"endDate\":\"2017,08,24\",\"headline\":\"<a href=\\\"http://dev.estim-science.fr/web/estim/agenda/-/evenements/import%20img2iopiopipo/1104\\\">Import IMG2iopiopipo</a>\",\"text\":\"<a href=\\\"http://dev.estim-science.fr/web/estim/annuaire/structure/-/structures/universcience/26221\\\">UNIVERSCIENCE</a>\",\"asset\":{\"media\":\"http://dev.estim-science.fr/image/image_gallery?img_id=40213\",\"thumbnail\":\"http://dev.estim-science.fr/image/image_gallery?img_id=40213\",\"credit\":\"\",\"caption\":\"\"}},{\"startDate\":\"2014,05,15\",\"endDate\":\"2016,05,07\",\"headline\":\"<a href=\\\"http://dev.estim-science.fr/web/estim/agenda/-/evenements/test-bug-3102-1/2401\\\">test-bug-3102-1</a>\",\"text\":\"<a href=\\\"http://dev.estim-science.fr/web/estim/annuaire/structure/-/structures/universcience/26221\\\">UNIVERSCIENCE</a>\",\"asset\":{\"media\":\"http://dev.estim-science.fr/image/image_gallery?img_id=59004\",\"thumbnail\":\"http://dev.estim-science.fr/image/image_gallery?img_id=59004\",\"credit\":\"\",\"caption\":\"\"}}]}}";
	
	public static void main(String[] args) {
//		escapeHtml(chaineTest);
//		escapeHtml(chaineTest2);
//		unescapeChaine( CHAINE_CONTEXT );
//		unescapeChaine( CHAINE_CONTEXT_RESSOURCE );
		escapeJson(JSON_VALUE);
		
	}
	
	
	public static void escapeJson (final String value) {
		String jsonValue = StringEscapeUtils.escapeJavaScript(value);
		System.out.println(jsonValue);
	}
	
	public static void escapeHtml(final String value) {
		System.out.println("EscapeHTML : ");
		String htmlValue = StringEscapeUtils.escapeHtml(value);
		System.out.println(htmlValue);
	}
	
	public static String unescapeChaine (String htmlChaine) {
		
		String urlDecode = null;
		
		try {
			urlDecode = URLDecoder.decode(htmlChaine, "UTF-8");
			System.out.println("urlDecode = " + urlDecode);
			String[] tabParams = urlDecode.split("&");
			Map<String, String> contextParams = null;
			if (tabParams != null && tabParams.length > 0) {
				contextParams = new HashMap<String, String>();
				List<String> params = Arrays.asList( tabParams );
				if (!params.isEmpty()) {
					for (String param : params) {
						if ( !StringUtils.isEmpty( param ) && param.startsWith("q=") ) {
							contextParams.put("q", param.replaceAll("q=", ""));
						} else if ( !StringUtils.isEmpty( param) && param.contains("r=") ) {
							String tmp = param.replaceAll("%2F", "/");
							// traitement d'une facette.
							String key = tmp.substring(tmp.indexOf("/")+1, tmp.lastIndexOf("/"));
							String value = tmp.substring(tmp.lastIndexOf("/")+1, tmp.length());
							contextParams.put(key, value);
						}
					}
				}
				// parcours de la map.
				for (Map.Entry<String, String> entry : contextParams.entrySet()) {
					System.out.println("key = " + entry.getKey() + " - value = " + entry.getValue());
				}
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
	
		return urlDecode;
		
	}
	
}
