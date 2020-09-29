package fr.exagone.jse.thread;
/**
 * Classe de test
 * - thread crée par extension
 * - un programme principal lance 2 threads.
 * @author Gildas
 *
 */
public class ThreadParExtension extends Thread {

	private int time;
	
	public ThreadParExtension(int time) {
		this.time = time;
	}
	
	public void run() {
		try {
			this.sleep(this.time);
			System.out.println("test");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println("Programme principal - Print1");
			ThreadParExtension thread1 = new ThreadParExtension(1000);
			ThreadParExtension thread2 = new ThreadParExtension(2000);
			thread1.start();
			thread2.start();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Programme principal - Print2");
	}	
	
}

