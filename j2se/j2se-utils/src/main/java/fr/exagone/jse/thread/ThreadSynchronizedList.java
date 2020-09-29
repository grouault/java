package fr.exagone.jse.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Principe: 2 threads sont en accès concurrent sur une liste.
 * Les deux thread font une mise à jour
 * Le programme principal fait une lecture des éléments de la liste.
 * 
 * Les processus sont synchronisés sur la liste.
 * Le processus fait un wait et les thread un notify pour avertir
 * le programme principal(pp) de l'ajour d'un String
 * 
 * Les opérations se font de manière concurrente.
 *
 * Résultat: il apparait 3 ou 4 nom
 * Le résultat dépend de l'ordonnacment.
 * thread1/2 - pp - thread1/2
 * thread1/2 - thread1/2 - pp
 * 
 * Dans tous les cas c'est toujours un thread qui s'exécute avant le pp.
 * 
 * @author Gildas
 *
 */
public class ThreadSynchronizedList {
	public static void main(String[] args) {
		
		// Création d'une liste
		List<String> liste = new ArrayList<String>();
		liste.add("Gildas");
		liste.add("Aurélie");
		
		// Création des threads
		ThreadList thread1 = new ThreadList(liste, 0000, "Justine");
		ThreadList thread2 = new ThreadList(liste, 0000, "Chloé");
		thread1.start();
		thread2.start();
		
		//Iteration sur les élément de la liste.
		boolean bListePleine = false;
		synchronized (liste) {
			while (!bListePleine) {
				try {
					liste.wait();
					for (Iterator<String> iterator = liste.iterator(); iterator.hasNext();) {
						String strName = iterator.next();
						System.out.println(strName);
					}
					System.out.println("---------");
					if (liste.size()>3) {
						bListePleine = true;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}

/**
 * Classe representant un thread d'accès à la liste.
 * @author Gildas
 *
 */
class ThreadList extends Thread{
	
	private List<String> objListe;
	private int nTime; //temps d attente
	private String strName;
	
	public ThreadList(List<String> pListe, int pTime, String pName) {
		this.objListe = pListe;
		this.nTime = pTime;
		this.strName = pName;
	}
	
	@Override
	public void run() {
		
		// on fait un sleep sur le thread, celà ne libère pas le verrou
		try {
			//ajout du nom à la liste
			synchronized (objListe) {
				Thread.sleep(this.nTime);
				this.objListe.add(this.strName);
				objListe.notify();
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		
	}

	public List<String> getObjListe() {
		return objListe;
	}

	public void setObjListe(List<String> objListe) {
		this.objListe = objListe;
	}
}
