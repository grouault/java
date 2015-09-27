package eni.jse.tp.langage;



public class Calculatrice {

	public static void main(String [] args){
		Calculatrice obj_Calculatrice = new Calculatrice();
		System.out.println(obj_Calculatrice.calcul('/', 2, 2)); 
	}	
	
	/**
	 * 
	 * @param operateur
	 * @param operande1
	 * @param operande2
	 */
	public double calcul(char operateur, double operande1, double operande2){
		double result;
        switch(operateur)
        {
            case '+':
                result = operande1 + operande2 ;
            break;
            case '-':
            	result = operande1 - operande2 ;
            break;
            case '*':
            	result = operande1 * operande2 ;
            break;
            case '/':
            	result = operande1 / operande2 ;
            break;
            default:
            	return 0;
        }		
		return result;
	}
	
}
