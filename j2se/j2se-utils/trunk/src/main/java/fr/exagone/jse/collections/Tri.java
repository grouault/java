package fr.exagone.jse.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fr.exagone.metier.Personne;

/**
 * Trier une liste de personne.
 * La classe Personne implémente l'interface Comparable
 * @author Gildas
 *
 */
public class Tri {
	public static void main(String[] args) {
		Personne p1 = new Personne("ROUAULT","Gildas");
		Personne p2 = new Personne("ROUAULT","Renée");
		Personne p3 = new Personne("GUERIN","Aurélie");
	
		List<Personne> lstPersonne = new ArrayList<Personne>();
		lstPersonne.add(p1);
		lstPersonne.add(p2);
		lstPersonne.add(p3);
		
		//iteration initial
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator.hasNext();) {
			Personne currentPersonne = iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		
		//affichage après tri
		Collections.sort(lstPersonne);
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator.hasNext();) {
			Personne currentPersonne = (Personne) iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		
	}
}
