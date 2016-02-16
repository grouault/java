package fr.exagone.jse.thread;
/**
 * Arrêter un thread.
 * Pour arrêter un thread, il est préferrable de passer par
 * un drapeau.
 * Dans le thread, on boucle tant que le drapeau vaut vrai.
 * A partir du programme principal ou autre thread, on met à jour
 * le drapeau du thread que l'on veut interrompre.
 * @author Gildas
 *
 */
public class ThreadStop extends Thread{

	private boolean bRun = true;
	
	@Override
	public void run() {
		while (bRun) {
			try {
				System.out.println("Thread Running");
				this.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread finished.");
	}

	/**
	 * @param run
	 */
	public void setBRun(boolean pRun) {
		bRun = pRun;
	}
	
	public static void main(String[] args) {
		System.out.println("Programme prinicpal: début");
		ThreadStop thread1 = new ThreadStop();
		thread1.start();
		try {
			Thread.sleep(2000);
			System.out.println("Arrêt du thread");
			thread1.setBRun(false);
			Thread.sleep(1000);
//			thread1.stop();
//			Thread.sleep(1000);
//			System.out.println("Démarrage du thread");
//			thread1.start();
//			Thread.sleep(2000);
//			System.out.println("Arrêt du thread");
//			thread1.setBRun(false);
//			Thread.sleep(1000);
	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Programme principal: fin");
	}
}
