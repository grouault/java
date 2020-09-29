package fr.exagone.jse.collections;


/**
 * Classe triant une liste de personne dans un ordre d�coissant � partir d'un
 * comparateur. La classe Personne a par d�faut un comparateur par ordre
 * croissant.
 * 
 * @author Gildas
 * 
 */
public class TriComparator {
//public class TriComparator implements Comparator<> {

	/**
	 * Trier les personne dans un ordre d�croissant
	 */
	/*
	public int compare(Personne p1, Personne p2) {
		int result = p2.getNom().compareToIgnoreCase(p1.getNom());
		if (result == 0) {
			result = p2.getPrenom().compareToIgnoreCase(p1.getPrenom());
		}
		return result;
	}

	public static void main(String[] args) {
		Personne p1 = new Personne("ROUAULT", "Gildas");
		Personne p2 = new Personne("ROUAULT", "Renée");
		Personne p3 = new Personne("GUERIN", "Aurélie");
		Personne p4 = new Personne("ROUAULT", "Marcel");
		
		List<Personne> lstPersonne = new ArrayList<Personne>();
		lstPersonne.add(p1);
		lstPersonne.add(p2);
		lstPersonne.add(p3);
		lstPersonne.add(p4);

		// iteration initial
		Collections.sort(lstPersonne);
		System.out
				.println("Liste triée avec la méthode comarable de la classe Personne");
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator
				.hasNext();) {
			Personne currentPersonne = iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		System.out.println("Max:" + (Collections.max(lstPersonne)).getPrenom());

		// affichage aprés tri
		System.out
				.println("-----------------------------------------------------------");
		Collections.sort(lstPersonne, new TriComparator());
		System.out.println("Liste triée: ordre décroissant du comparateur");
		for (Iterator<Personne> iterator = lstPersonne.iterator(); iterator
				.hasNext();) {
			Personne currentPersonne = (Personne) iterator.next();
			System.out.println(currentPersonne.getPrenom());
		}
		System.out.println("Max:"
				+ (Collections.max(lstPersonne, new TriComparator()))
						.getPrenom());

	}
	*/
}
