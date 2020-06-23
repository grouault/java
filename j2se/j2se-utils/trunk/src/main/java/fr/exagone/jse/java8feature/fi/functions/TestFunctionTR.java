package fr.exagone.jse.java8feature.fi.functions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Exemple d'utilisation de la fonction Function<T,R> sur la méthode 
 * - Map.computeIfAbsent()
 * 
 * @author grouault
 *
 */
public class TestFunctionTR {

	public static void main(String[] args) {
		
		TestFunctionTRwithMap();
		
		TestFunctionTRCompose();
		
	}
	
	public static void TestFunctionTRCompose() {
		
		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";
		// combinaison de la fonction quote appliquée sur le résultat de la fonction intToString
		Function<Integer, String> quoteIntToString = quote.compose(intToString);
		System.out.println("TestFunctionTRCompose: " + quoteIntToString.apply(5));

	}
	
	public static void TestFunctionTRwithMap() {
		
		Map<String, Integer> nameMap = new HashMap<>();
		nameMap.put("Gildas", 12);
		
		// 1- lambda
		Function<String, Integer> lambda = s -> s.length();
		Integer value = nameMap.computeIfAbsent("Gildas", lambda);
		System.out.println("TestFunctionTRwithMap : value = " + value);
		
		// 2- methode-reference
		Function<String, Integer> methodFunction = String::length;
		
		// L'objet surlequel le lambda / MethodReference est appelé est en fait l'argument implicit (le premier) de la méthode
		// computeIfAbsent qui permet de caster une lambda / MethodeReference 'functionNameLength' en function interface.
		value = nameMap.computeIfAbsent("John", methodFunction);
		System.out.println("TestFunctionTRwithMap : value = " + value);
	
	}
	
}
