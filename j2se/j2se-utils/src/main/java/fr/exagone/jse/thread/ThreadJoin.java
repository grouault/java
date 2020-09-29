package fr.exagone.jse.thread;

/**
 * Principe: join
 * Appliqu� sur un thread, cette m�thode demande au thread/programme
 * courant d'attendre la fin d'ex�cution de ce thread.
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
			System.out.println("Programme principal: d�but");
			ThreadJoin thread1 = new ThreadJoin();
			thread1.start();
			Thread.sleep(1000);
			System.out.println("Programme principal: intermediaire");
			// on bloque le thread courant jusqu'� ce que le thread1 a termin�
			// son travail
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Programme principal: fin");
	}
}
