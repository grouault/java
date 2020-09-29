package fr.exagone.utils.string;

public class StringReplace {

	private static final String testChaine = "Thématiques";
	private static final String testChaine2 = "Public cible test test";
	private static final String testChaine3 = "&,é,#";
	private static final String TEST_CHAINE_PLUS = "agronomei+espace";
	
	private static String testChaine4 = "Le chien.pdf est un fichier.";
	
	public static void main(String[] args) {
	
		String replace1 = testChaine2.replace("[^a-zA-Z0-9]", "_");
		String replace2 = replace1.replaceAll("[\\s]", "_");
//		String replace2 = replace1.replace(" ", "_");
		System.out.println("replace 1 = " + replace1);
//		String replace2 = replace1.replace("�", "e");
		System.out.println("replace 2 = " + replace2);
		 String replace3 = replace2.replaceAll("[���]", "e");
		System.out.println("replace 3 = " + replace3);
//		replaceAscii(testChaine3);
		replaceRegExp();
		replaceRegExpPlus();
	}
	
	
	public static void replaceRegExpPlus() {
		System.out.println("Test chaine plus = " + TEST_CHAINE_PLUS.replaceAll("\\+", " "));
	}
	
	public static void replaceRegExp() {
		if (testChaine4.endsWith(".")) {
			testChaine4 = removeLastChar(testChaine4);
		}
		testChaine4 = clean( testChaine4 );
		System.out.println("replace = " + testChaine4);
	}
	
	public static void replaceAscii (String initChaine) {
		String replaceOne = initChaine.toLowerCase().replaceAll("[����]", "e");
		String replaceSecond = replaceOne.replaceAll("[���]", "u");
		String replaceThird = replaceSecond.replaceAll("[��]", "o");
		String replaceFour = replaceThird.replaceAll("[���]", "a");
		String replaceFive = replaceFour.replaceAll("[��]", "i");
		String replaceFinal = replaceFive.replace("[^a-zA-Z0-9]", "-");
		System.out.println("-- Replace Ascii --");
		System.out.println("replace final = " + replaceFinal);
	}
	
	private static String clean(String str) {
		return str.replaceAll("[.]", "_");
	}
	
    private static String removeLastChar(String str) {
        return str.substring(0, str.length()-1);
    }
    
}
