package fr.exagone.jse.collections.list;

import java.util.Comparator;

/**
 * Tri un tableau de String sur le premier champ.
 * @author Rouault1
 *
 */
public class CollectionSortComparator implements Comparator<String[]> {

	public int compare(String[] chaine1, String[] chaine2) {
		String str1 = chaine1[0];
		String str2 = chaine2[0];
		return str1.compareTo(str2);
	}

}
/*
public class ComparatorTabString implements Comparator {

	public int compare(Object o1, Object o2) {
		String str1 = ((String[])o1)[0];
		String str2 = ((String[])o2)[0];
		return str1.compareTo(str2);
	}

}
*/
