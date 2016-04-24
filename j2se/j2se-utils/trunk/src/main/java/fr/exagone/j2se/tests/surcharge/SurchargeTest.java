package fr.exagone.j2se.tests.surcharge;

import java.util.ArrayList;
import java.util.List;

public class SurchargeTest {

	public static void main(String[] args) {
		test("Gildas", "Rouault");
	}
	
	public static void test(String param1, String param2) {
		List<String> myList = new ArrayList<String>();
		myList.add(param1);
		myList.add(param2);
		System.out.println(String.join("#", myList));
	}
	
	/**
	 * Surcharge la méthode test avec 3 paramètres.
	 * Le modificateur d'accès change
	 * Le modificateur ne permet pas la surcharge à lui seul.
	 * @param param1
	 * @param param2
	 * @param param3
	 */
	protected static void test(String param1, String param2, String param3) {
		List<String> myList = new ArrayList<String>();
		myList.add(param1);
		myList.add(param2);
		System.out.println(String.join("#", myList));
	}
}
