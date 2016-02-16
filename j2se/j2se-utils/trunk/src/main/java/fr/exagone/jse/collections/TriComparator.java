package fr.exagone.jse.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import fr.exagone.metier.Personne;

/**
 * Classe triant une liste de personne dans un ordre d�coissant � partir d'un
 * comparateur. La classe Personne a par d�faut un comparateur par ordre
 * croissant.
 * 
 * @author Gildas
 * 
 */
public class TriComparator implements Comparator<Personne> {

	/**
	 * Trier les personne dans un ordre d�croissant
	 */
	public int compare(Personne p1, Personne p2) {
		int result = p2.getNom().compareToIgnoreCase(p1.getNom());
		if (result == 0) {
			result = p2.getPrenom().compareToIgnoreCase(p1.getPrenom());
		}
		return result;
	}

	public static void main(String[] args) {
		Personne p1 = new Personne("ROUAULT", "Gildas");
		Personne p2 = new Personne("ROUAULT", "Ren�e");
		Personne p3 = new Personne("GUERIN", "Aur�lie");

		List<Personne> lstPersonne = new ArrayList<Personne>();
		lstPersonne.add(p1);
		lstPersonne.add(p2);
		lstPersonne.add(p3);

		// iteration initial
		Collections.sort(lstPersonne);
		System.out
				.println("Liste tri�e avec la m�thode comarable de la classe Personne");
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator
				.hasNext();) {
			Personne currentPersonne = iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		System.out.println("Max:" + (Collections.max(lstPersonne)).getPrenom());

		// affichage apr�s tri
		System.out
				.println("-----------------------------------------------------------");
		Collections.sort(lstPersonne, new TriComparator());
		System.out.println("Liste tri�e: ordre d�croissant du comparateur");
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator
				.hasNext();) {
			Personne currentPersonne = (Personne) iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		System.out.println("Max:"
				+ (Collections.max(lstPersonne, new TriComparator()))
						.getPrenom());

	}

}
