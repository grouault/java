package fr.exagone.jse.thread;

/**
 * Cette classe définit un thread recevant un objet threadSafe. Le thread met à
 * jour le compteur de l'objet threadSafe. Le but est de vérifier que lors de la
 * mise à jour par deux threads au même moment, le compteur est incrémenté de la
 * bonne manière
 * 
 * Pour tester, enlever le mot-clé synchronized sur la méthode adjust de la
 * class ThreadSafe Résultat attendu en cas normal: 2 Résultat incohérent: 1
 * Dans le cas normal, le thread 2 est bloqué à l'entrée de la méthode
 * synchronized
 * 
 * Note: le paramètre time permet de définir une période d'attente dans la
 * méthode adjust pour que le deuxième thread exécute cette méthode en même
 * temps que le premier thread.
 * 
 * @author Gildas
 * 
 */
public class ThreadSynchronized extends Thread {

	private String id;
	private ThreadSafe objThreadSafe;
	private int time;

	public ThreadSynchronized(String strId, ThreadSafe pObjThreadSafe, int pTime) {
		this.id = strId;
		this.objThreadSafe = pObjThreadSafe;
		this.time = pTime;
	}

	@Override
	public void run() {
		System.out.println("Message thread: " + this.id);
		this.objThreadSafe.adjust(this.time, this.id);
	}

	public static void main(String[] args) {
		try {
			System.out.println("Programme principal: début");
			// Creation d un objet threadSafe
			ThreadSafe objThreadSafe = new ThreadSafe();
			ThreadSynchronized thread1 = new ThreadSynchronized("thread1",
					objThreadSafe, 2000);
			ThreadSynchronized thread2 = new ThreadSynchronized("thread2",
					objThreadSafe, 0);
			thread1.start();
			Thread.sleep(500);
			System.out.println("cpt- 1 = " + objThreadSafe.getNCpt());
			thread2.start();
			Thread.sleep(500);
			System.out.println("cpt- 2 = " + objThreadSafe.getNCpt());
			Thread.sleep(3000);
			System.out.println("cptFinal = " + objThreadSafe.getNCpt());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Programme principal: fin");
	}

}

/**
 * pour definir une class ThreadSafe Cette classe définit un compteur, pouvant
 * être mise à jour par plusieurs threads
 * 
 * @author Gildas
 * 
 */
class ThreadSafe {

	private int nCpt;

	public ThreadSafe() {
		this.nCpt = 0;

	}

	public synchronized void adjust(int time, String pThread) {
		int cptLocal = this.nCpt;
		try {
			System.out.println(pThread + " / adjustEntrée - cptLocal="
					+ this.nCpt);
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.nCpt = (cptLocal + 1);
		System.out.println(pThread + " / adjustSortie - cptLocal=" + this.nCpt);
	}

	public int getNCpt() {
		return nCpt;
	}

	public void setNCpt(int cpt) {
		nCpt = cpt;
	}

}
