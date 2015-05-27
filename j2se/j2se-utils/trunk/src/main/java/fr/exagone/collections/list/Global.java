package fr.exagone.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Global {

	public static void main(String[] args) {
      sublist(); //sublist.
	}
	
	/**
	 * extrait une partie de la liste.
	 * - idéale pour faire des opéations sur une partie de la liste.
	 * - toute modification d'une élément de la sous liste est répercutée sur la liste
	 * initiale.
	 */
	private static void sublist(){
		String  ar[]={"India","Pakistan","United Kingdom","Japan","Korea"};
		ArrayList<String> list=new ArrayList<String>();
		list.add(ar[0]);
		list.add(ar[1]);
		list.add(ar[2]);
		list.add(ar[3]);
		List<String> list1=list.subList(1,3);
        System.out.println("-- Liste initial --");
		System.out.println(list+ " ");
		System.out.println(list1 + " ");
		System.out.println("-- Ajout de la France à la sous-liste --");
		list1.add(1, "Francia"); //ajout d'un élément à la sous liste.
		System.out.println(list + " ");
		System.out.println(list1 + " ");
		System.out.println("-- Tri sur la sous liste de la sous-liste --");
		Collections.sort(list1);
		System.out.println(list + " ");
		System.out.println(list1 + " ");		
		list1.clear(); // suppression des éléments de la sous liste.
		System.out.println("-- Suppression des éléments de la sous-liste --");
		System.out.println(list + " ");
		System.out.println(list1 + " ");
	}

	
}
