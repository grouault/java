package fr.exagone.jse.java8feature.fi.definition;
/**
 * interface fonctionnelle Square-Root
 * - accepte un input de type int et retourne une valeur de type double
 * ==> peut-être facilement implémenter par un lambda
 * 
 * @author grouault
 *
 */
@FunctionalInterface
public interface SquareRoot {

	abstract double findSquareRoot(int n);
	
}
