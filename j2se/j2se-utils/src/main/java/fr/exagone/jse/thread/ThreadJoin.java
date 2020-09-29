package fr.exagone.jse.thread;

/**
 * Principe: join
 * Appliqué sur un thread, cette méthode demande au thread/programme
 * courant d'attendre la fin d'exécution de ce thread.
 */
public class ThreadJoin extends Thread {

	@Override
	public void run() {

		try {
			System.out.println("Message Thread: 1");
			this.sleep(3000);
			System.out.println("Message Thread: 2");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("Programme principal: début");
			ThreadJoin thread1 = new ThreadJoin();
			thread1.start();
			Thread.sleep(1000);
			System.out.println("Programme principal: intermediaire");
			// on bloque le thread courant jusqu'à ce que le thread1 a terminé
			// son travail
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Programme principal: fin");
	}
}
