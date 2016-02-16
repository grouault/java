package eni.jse.tp.langage;

/**
 * Classe générant les tables de multiplication.
 * @author Gildas
 *	
 */
public class TestCalcul {

	public static void main(String[]args){
		tableMultiplication();
		tableDivision();
	}

	/**
	 * Génère les tables de division
	 */
	public static void tableDivision() {
		//compteur de boucle
		int i;
		int j;
		StringBuffer strBuffer = new StringBuffer();
		for(i=-9; i<=9; i++){
			j=0;
			strBuffer.append("-- Table des ").append(i).append(" --\n");
			while(j<10){
				j++;
				strBuffer.append(i).append('/').append(j).append('=').append(i/j).append("\n");
			}
			strBuffer.append("\n");
		}
		/* affichage écran */
		System.out.println(strBuffer);
	}
	
	/**
	 * Genere les tables de multiplication.
	 */
	public static void tableMultiplication(){
		//compteur de boucle
		int i;
		int j;
		StringBuffer strBuffer = new StringBuffer();
		for(i=1; i<=9; i++){
			j=0;
			strBuffer.append("-- Table des ").append(i).append(" --\n");
			while(j<10){
				j++;
				strBuffer.append(i).append('*').append(j).append('=').append(i*j).append("\n");
			}
			strBuffer.append("\n");
		}
		/* affichage écran */
		System.out.println(strBuffer);
	}
	
}


