package fr.exagone.jse.thread;

/**
 * Cette classe d�finit un thread recevant un objet threadSafe. Le thread met �
 * jour le compteur de l'objet threadSafe. Le but est de v�rifier que lors de la
 * mise � jour par deux threads au m�me moment, le compteur est incr�ment� de la
 * bonne mani�re
 * 
 * Pour tester, enlever le mot-cl� synchronized sur la m�thode adjust de la
 * class ThreadSafe R�sultat attendu en cas normal: 2 R�sultat incoh�rent: 1
 * Dans le cas normal, le thread 2 est bloqu� � l'entr�e de la m�thode
 * synchronized
 * 
 * Note: le param�tre time permet de d�finir une p�riode d'attente dans la
 * m�thode adjust pour que le deuxi�me thread ex�cute cette m�thode en m�me
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
			System.out.println("Programme principal: d�but");
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
 * pour definir une class ThreadSafe Cette classe d�finit un compteur, pouvant
 * �tre mise � jour par plusieurs threads
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
			System.out.println(pThread + " / adjustEntr�e - cptLocal="
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
