package fr.exagone.collections.list;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	public static List<String> lstchats = new ArrayList<String>();
	static {
		lstchats.add("max");
		lstchats.add("medudor");
		lstchats.add("polo");
	}
	
	public static void main(String[] args) {
		doubleIterator();
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
