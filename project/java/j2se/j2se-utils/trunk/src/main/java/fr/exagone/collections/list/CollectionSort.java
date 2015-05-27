package fr.exagone.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionSort {

	public static void main(String[] args) {
		System.out.println("test");
		List<String[]> listTabString = getSort();
		for (String[] current : listTabString) {
			System.out.println(current[0]);
		}
		
	}
	
	public static List<String[]> getSort () {
		List<String[]> listTabString = new ArrayList<String[]>();
		listTabString.add(new String[]{"titi","ceci est le nom de titi."});
		listTabString.add(new String[]{"mimi","ceci est le nom de mimi."});
		listTabString.add(new String[]{"popo","ceci est le nom de popo."});
		Collections.sort(listTabString, new CollectionSortComparator());
		return listTabString;
	}
	
}
