package fr.exagone.j2se.tests.type;

public class BooleanTest {

	public static void main(String[] args) {
		
		testReferenceNull();
		
	}
	
	/**
	 * Test Reference Null.
	 * ==> Declenche un NullPointer
	 */
	public static void testReferenceNull() {
		Boolean bTest = null;
		if (bTest) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	
}
