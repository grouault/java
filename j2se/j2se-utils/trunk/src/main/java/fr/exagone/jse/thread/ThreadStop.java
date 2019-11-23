package fr.exagone.jse.thread;
/**
 * Arr�ter un thread.
 * Pour arr�ter un thread, il est pr�ferrable de passer par
 * un drapeau.
 * Dans le thread, on boucle tant que le drapeau vaut vrai.
 * A partir du programme principal ou autre thread, on met � jour
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
				System.out.println("Thread stop Running");
				ThreadStop.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread stop finished.");
	}

	/**
	 * @param run
	 */
	public void setBRun(boolean pRun) {
		bRun = pRun;
	}
	
	public static void main(String[] args) {
		System.out.println("Programme principal: début");
		ThreadStop thread1 = new ThreadStop();
		thread1.start();
		try {
			Thread.sleep(2000);
			System.out.println("Arret du thread");
			thread1.setBRun(false);
			Thread.sleep(1000);
//			thread1.stop();
//			Thread.sleep(1000);
//			System.out.println("D�marrage du thread");
//			thread1.start();
//			Thread.sleep(2000);
//			System.out.println("Arr�t du thread");
//			thread1.setBRun(false);
//			Thread.sleep(1000);
	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Programme princial: fin");
	}
}
