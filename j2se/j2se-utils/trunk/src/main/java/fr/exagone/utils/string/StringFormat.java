package fr.exagone.utils.string;


public class StringFormat {

	
	private static final String TEST_CHAINE = "'http://localhost:8080/widget/web/estim/carrousel-next-evenement/-/widgetcarrouselnextevent_WAR_estimportailportlet?_widgetcarrouselnextevent_WAR_estimportailportlet_idsd=34701'";
	
	public static void main(String[] args) {

		String html = "";
        int strSize = TEST_CHAINE.length();
        System.out.println("strSize = " + strSize);
        int lineNbCar = 65;
        int nbIter = (int)Math.floor(strSize / lineNbCar) > 0 ? (int)Math.floor(strSize / lineNbCar) : 1;
        System.out.println("nbIter = " + nbIter);
        for (int i = 0; i <=  nbIter; i++) {
        	if (i < nbIter) {
        		html += TEST_CHAINE.substring(i*lineNbCar, i*lineNbCar + lineNbCar) + "\n" + "\t" + "\t" + "\t";
        	} else {
        		html += TEST_CHAINE.substring(i*lineNbCar, TEST_CHAINE.length()) + "\n" + "\t" + "\t" + "\t";	
        	}
        }
        System.out.println("html = " + html);	
	}
	
	
}
