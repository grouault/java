package fr.exagone.jse.java8feature.functionalinterface;

public class SquareRootImpl implements SquareRoot {

	@Override
	public double findSquareRoot(int n) {

		return Math.sqrt(n);
	
	}

}
