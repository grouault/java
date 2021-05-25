package fr.exagone.lambda;

import org.junit.Test;

import fr.exagone.jse.java8feature.fi.definition.Operation;
import junit.framework.Assert;

public class TestLambda {

	@Test
	public void TestOperation() {
		
		// operation addition
		Operation addition = (a,b) -> a + b;
		double resultAddition = executeOperation(addition, 2, 3);
		Assert.assertEquals(5d, resultAddition, 0);
		
		// operation soustraction
		Operation soustraction = (a, b) -> a -b;
		double resultSoustraction = executeOperation(soustraction, 2, 2);
		Assert.assertEquals(0d, resultSoustraction, 0);
		
		// operation multiplication
		Operation multiplication = (a,b) -> (a * b);
		Double resultMultiplication = executeOperation(multiplication, 3, 5);
		Assert.assertEquals(15d, resultMultiplication, 0);
		
		// operation division
		Operation division = (a,b) -> (a/b);
		Double resultDivision = executeOperation(division, 5, 3);
		Assert.assertEquals(1.666d, resultDivision, 0.01d);
		
	}
	
	/**
	 * fonction générique permetttant d'evaluer l'opération.
	 * 
	 * @param operation
	 * @param a
	 * @param b
	 * @return
	 */
	private Double executeOperation(Operation operation, int a, int b) {
		Double result = operation.evaluer(a, b);
		System.out.println("result operation = " + result);
		return result;
	}
	
}
