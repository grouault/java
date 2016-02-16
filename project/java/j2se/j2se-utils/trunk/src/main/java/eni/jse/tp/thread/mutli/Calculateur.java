package eni.jse.tp.thread.mutli;

/**
 * représente une machine disposant de 5 calculateurs.
 * @author Gildas
 *
 */
public class Calculateur {

	private Semaphore sem;
	private static int cptThread;
	
	/**
	 * initialisation du sémaphore: 5 jetons
	 */
	public Calculateur() {
		this.sem = new Semaphore(5);
		Calculateur.cptThread = 0;
	}
	
	/**
	 * effectue un calcul pour un thread donné.
	 */
	public void calcul() {

		try {
			this.sem.acceder(Thread.currentThread().getName());
			String strMessage = "Calculateur: " + Thread.currentThread().getName();
			System.out.println(strMessage);
			Thread.sleep(2000);
			this.sem.liberer(Thread.currentThread().getName());
			Calculateur.cptThread ++;
			System.out.println("cptThread: " + Calculateur.cptThread);
			if (Calculateur.cptThread>9) {
				synchronized (this) {
					this.notify();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
