package fr.exagone.jse.collections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.tree.SingleIterator;

import java.util.AbstractMap.SimpleImmutableEntry;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

public class ListIterator {

	public static List<String> lstchats = new ArrayList<String>();
	
	static {
		lstchats.add("max");
		lstchats.add("medudor");
		lstchats.add("polo");
	}
	
	public static void main(String[] args) {
		simpleIterator();
		doubleIterator();
	}
	
	private static void simpleIterator() {
		Iterator<String> it = lstchats.iterator();
		// remove polo.
		for (;it.hasNext();) {
			String current = it.next();
			if (current.equals("polo")) {
				it.remove();
			}
		}
		Iterator<String> it2 = lstchats.iterator();
		System.out.println("List size = " + lstchats.size());
		for (;it2.hasNext();) {
			String current = it2.next();
			System.out.println("current = " + current);
		}
	}
	
	private static void doubleIterator(){
		for (String cat1 : lstchats) {
			System.out.println("---------------------");
			for (String cat2 : lstchats) {
				System.out.println(cat1 + " - " + cat2);
			}
		}
	}
	
}
