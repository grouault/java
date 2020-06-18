package fr.exagone.jse.java8feature.functionalinterface;

public class UseFoo {

	private String value = "Enclosing scope value";

	public String scopeExperiment() {
		
		// classe interne
		Foo fooIC = new Foo() {
			String value = "Inner class value";

			@Override
			public String method(String string) {
				return this.value;
			}
		};
		String resultIC = fooIC.method("");
		
		Foo fooLambda = (n) -> {
			String value = "Lambda  value";
			return this.value;
		};
		String resultLambda = fooLambda.method("");
		
		return "Results: resultIC = " + resultIC + ", resultLambda = " + resultLambda;
		
	}

		
	
}
