package fr.exagone.jse.java8feature.fi.custom;

/**
 * conversion byte to short
 * 
 * @author grouault
 *
 */
@FunctionalInterface
public interface ShortToByteFunction {

	byte applyAsByte(short s);
	
}
