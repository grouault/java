package fr.exagone.jse.java8feature.fi.scope;

import java.util.function.Function;

/**
 * Classe pour tester le scope / la portée d'un lambda
 * 1- classe : définition d'une variable de classe 'value'
 * 2- méthode scopeExperiment : 
 * -- implémentation int. fonct. Foo par classe anonyme: rédéfinition de 'value' dans le scope de la classe anonyme
 * ---- this.value => prend la valeur de la fonction anonyme
 * -- implémentation int. fonct. Foo par Lambda : rédéfinition de 'value' ==>
 * ---- this.value => prend la valeur du scope de la classe englobante
 *  
 * @author gildas
 *
 */
public class UseFoo {

	// valeur mise dans le scope de la classe.
	private String value = "Enclosing scope classe value";

	public void scopeExperiment() {
		
		String valueMethod = "value-method define in scopeExperiment";
		
		// Implémentation via classe interne
		Foo fooIC = new Foo() {
			
			String valueMethod = "value-method define in class anonyme Foo";
					
			// redéfition dans le scope de la classe interne.
			String value = "Inner class value : " + valueMethod;

			@Override
			public String method(String string) {
				return this.value;
			}
		};
		String resultIC = fooIC.method("");
		
		// Implémentation par lambda
		Foo fooLambda = (n) -> {
			// redéfintion dans le scope  du lambda
			// this.value prend la valeur du scpoe englobant.
			String value = "lambda value : " + valueMethod;
			return this.value + " - " + valueMethod;
		};
		String resultLambda = fooLambda.method("");
		
		// Implémentation par fonction
		Function<String, String> fooFunc = (s) -> {
			String value = "function value - valueMethod = " + valueMethod;
			return this.value  + " - " + valueMethod;
		};
		String resultFunc = fooFunc.apply("");
		
		// Print-result
		System.out.println("Results: resultIC = " + resultIC);
		System.out.println("Results: resultLambda = " + resultLambda);
		System.out.println("Results: fooFunc = " + resultFunc);
		
	}

		
	
}
