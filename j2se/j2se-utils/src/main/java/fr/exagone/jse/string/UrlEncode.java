package fr.exagone.jse.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlEncode {

	private static final String chaineToEncodeWithQuote = "\"pensaon\"\"";
	private static final String chaineToEncode = "{\"titre\":\"pinocchioUnitTest2\","
			+ "\"description\":\"Un évènement à ne pas manquer\","
			+ "\"linkWebsiteEvent\":\"http::sep::sep:www.jeuxvideo.com:sep:\","
			+ "\"linkIllustration\":\"http::sep::sep:upload.wikimedia.org:sep:wikipedia:sep:commons:sep:thumb:sep:9:sep:94:sep:Pinocchio_1940.jpg:sep:290px-Pinocchio_1940.jpg\",\"copyrightIllustration\":\"aucuns droits sur l'illustration\",\"linkAffiche\":\"\",\"copyrightAffiche\":\"aucuns droits sur l'affiche\",\"linkVignette\":\"\",\"linkPlaquette\":\"\",\"dateDebut\":1413676800000,\"dateFin\":1444780800000,\"datePublication\":1413763200000,\"structureOrga\":19933,\"adresse\":\"1 Rue Denis Poisson, 75017 Paris, Ile-de-France, France\",\"accessibilites\":[\"SOURDS_MALENTENDANTS\"],\"categorieEvenement\":\"PROFESSIONNEL\",\"evenementNational\":\"EVT_NAT_FET_SCI\",\"langues\":[\"ANGLAIS\",\"FRANCAIS\"],\"publicCibles\":[\"A_PARTIR_DE_2_ANS\",\"PARTENAIRE_MECENE\"],\"thematiques\":[\"ESPACE_ASTRONOMIE\"],\"typeEvenements\":[\"EXPOSITIONS_VISITES\"],\"partenaire\":\"\",\"tarif\":\"23Euros, 30Euros\"}";
	
	private static final String chaineToDecode = "%7B%22titre%22%3A%22pinocchioUnitTest2%22%2C%22description%22%3A%22Un+%C3%A9v%C3%A8nement+%C3%A0+ne+pas+manquer%22%2C%22linkWebsiteEvent%22%3A%22http%3A%3Asep%3A%3Asep%3Awww.jeuxvideo.com%3Asep%3A%22%2C%22linkIllustration%22%3A%22http%3A%3Asep%3A%3Asep%3Aupload.wikimedia.org%3Asep%3Awikipedia%3Asep%3Acommons%3Asep%3Athumb%3Asep%3A9%3Asep%3A94%3Asep%3APinocchio_1940.jpg%3Asep%3A290px-Pinocchio_1940.jpg%22%2C%22copyright%22%3A%22aucuns+droits+sur+l%27illustration%22%2C%22linkAffiche%22%3A%22%22%2C%22linkVignette%22%3A%22%22%2C%22linkPlaquette%22%3A%22%22%2C%22dateDebut%22%3A1413676800000%2C%22dateFin%22%3A1444780800000%2C%22datePublication%22%3A1413763200000%2C%22structureOrga%22%3A19933%2C%22adresse%22%3A%221+Rue+Denis+Poisson%2C+75017+Paris%2C+Ile-de-France%2C+France%22%2C%22accessibilites%22%3A%5B%22SOURDS_MALENTENDANTS%22%5D%2C%22categorieEvenement%22%3A%22PROFESSIONNEL%22%2C%22evenementNational%22%3A%22EVT_NAT_FET_SCI%22%2C%22langues%22%3A%5B%22ANGLAIS%22%2C%22FRANCAIS%22%5D%2C%22publicCibles%22%3A%5B%22A_PARTIR_DE_2_ANS%22%2C%22PARTENAIRE_MECENE%22%5D%2C%22thematiques%22%3A%5B%22ESPACE_ASTRONOMIE%22%5D%2C%22typeEvenements%22%3A%5B%22EXPOSITIONS_VISITES%22%5D%2C%22contacts%22%3A%5B%22srunjanally%40augustareeves.fr%22%5D%2C%22partenaire%22%3A%22%22%2C%22tarif%22%3A%2223Euros%2C+30Euros%22%7D";
	
	
	public static void main(String[] args) {
		encodeUrl(chaineToEncodeWithQuote);
//		decodeUrl( chaineToDecode );
	}
	
	public static void encodeUrl (final String value) {
		System.out.println("Encode URL : ");
		try {
			String encodeValue = urlEncoder(value);
			System.out.println(encodeValue);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
	
	protected static String urlEncoder (String str) throws UnsupportedEncodingException {
		str = URLEncoder.encode(str, StandardCharsets.UTF_8.name())
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
		return str;
	}	
	
	public static void decodeUrl (String value) {
		System.out.println("Decode URL :");
		try {
			value = URLDecoder.decode(value, StandardCharsets.UTF_8.name());
			System.out.println(value);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error = " + e.getMessage());
		}

	}
	
}
