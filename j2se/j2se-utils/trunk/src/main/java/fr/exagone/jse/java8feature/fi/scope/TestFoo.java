package fr.exagone.jse.java8feature.fi.scope;

public class TestFoo {

	public static void main(String[] args) {
		testScopeLambda();
	}
	
	////////////////////
	// Test scope lambda
	private static void testScopeLambda() {
		UseFoo useFoo = new UseFoo();
		useFoo.scopeExperiment();
	}
	
}
