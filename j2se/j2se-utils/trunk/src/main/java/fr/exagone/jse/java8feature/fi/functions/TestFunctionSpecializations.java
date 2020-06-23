package fr.exagone.jse.java8feature.fi.functions;

import java.util.function.DoubleToIntFunction;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

import fr.exagone.jse.java8feature.fi.custom.ShortToByteFunction;

/**
 * Test des fonctions qui permettent de tester les types primitifs.
 * 
 * @author grouault
 *
 */
public class TestFunctionSpecializations {

	public static void main(String[] args) {
		TestCastIntToInteger();
		TestCastIntegerToInt();
		TestCastStringToInt();
		TestCastDoubleToInt();
		TestCastShortToByte();
	}
	
	// Function IntFunction : cast int to Integer
	public static void TestCastIntToInteger() {
		IntFunction<Integer> testIntFunction = Integer::valueOf;
		Integer test = testIntFunction.apply(5);
		System.out.println("int to Integer = " + test);
	}
	
	// Function ToIntFunction : Integer cast to int
	public static void TestCastIntegerToInt() {
		ToIntFunction<Integer> testToIntFunction = Integer::intValue;
		int value = testToIntFunction.applyAsInt(5);
		System.out.println("Integer as int : value = " + value);
	}
	
	// Function ToIntFunction : String cast to int
	public static void TestCastStringToInt() {
		ToIntFunction<String> testToIntFunction = Integer::parseInt;
		int value = testToIntFunction.applyAsInt("5");
		System.out.println("String as int : value = " + value);
	}
	
	// Function DoubleToInt : double cast to int
	public static void TestCastDoubleToInt() {
		DoubleToIntFunction doubleToInt = d -> (int)d;
		int value = doubleToInt.applyAsInt(2.1d);
		System.out.println("double to int : value = " + value);
	}

	// fonction de transformation d'un array de short en byte.
	public static byte[] transformArray(short[] array, ShortToByteFunction function) {
		byte[] toReturn = new byte[array.length];
		for (int i = 0 ; i < array.length ; i++) {
			toReturn[i] = function.applyAsByte(array[i]);	
		}
		return toReturn;
	}
	
	// Fonction ShortToByte
	public static void TestCastShortToByte() {
		short[] array = {(short)1, (short)2, (short)3};
		ShortToByteFunction function = s -> (byte)s; 
		byte[] result = transformArray(array, function);
		System.out.println("short to byte : value = " + result);
	}
	
}
