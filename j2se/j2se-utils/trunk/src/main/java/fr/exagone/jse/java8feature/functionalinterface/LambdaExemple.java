package fr.exagone.jse.java8feature.functionalinterface;

import java.util.function.Function;

public class LambdaExemple {

	public static void main(String[] args) {
		testBeforeJava8();
		testAnonymousFonction();
		testWithLambdaExpression();
		testWithLambdaAsParameter();
		testAsFunction();
		testScopeLambda();
	}
	
	/////////////////
	// BEFORE JAVA 8
	/////////////////
	public static void testBeforeJava8() {
		// utilisation d'une implémentation de l'interface fonctionnelle
		SquareRoot squareRoot = new SquareRootImpl();
		System.out.println("testBeforeJava8()" + squareRoot.findSquareRoot(9));
	}
	
	public static void testAnonymousFonction() {
		
		// utilisation d'une fonction anonyme
		SquareRoot squareRoot = new SquareRoot() {
			@Override
			public double findSquareRoot(int n) {
				return Math.sqrt(n);
			}
		};
		System.out.println("testAnonymousFonction() = " + squareRoot.findSquareRoot(16));
		
	}

	//////////////////
	// AFTER JAVA 8
	//////////////////
	
	// LAMBDA expression
	public static void testWithLambdaExpression() {
		// implementation par fonction lambda
		SquareRoot squareRoot = (n) -> (Math.sqrt(n));
		System.out.println("testWithLambdaExpression = " + squareRoot.findSquareRoot(25));
	}

	//////////////////////////////////////////////////////
	// Functional interface as Lambda and use as Parameter
	private static double displayData(SquareRoot squareRoot, int i){
		return squareRoot.findSquareRoot(i);
	}
	public static void testWithLambdaAsParameter() {
		SquareRoot squareRoot = (n) -> (Math.sqrt(n));
		System.out.println("testWithLambdaAsParameter = " + displayData(squareRoot, 36));
	}
	
	/////////////////////////////////////////
	// Sans interface mais avec Function<T,R>
	private static double displayData(Function<Integer, Double> fn, int i){
		return fn.apply(i);
	}
	public static void testAsFunction() {
		Function<Integer, Double> fn = (n) -> (Math.sqrt(n));
		System.out.println("testAsFunction = " + displayData(fn, 49));
	}

	////////////////////
	// Test scope lambda
	private static void testScopeLambda() {
		UseFoo useFoo = new UseFoo();
		System.out.println("testScopeLambda = " + useFoo.scopeExperiment());
	}
	
}
