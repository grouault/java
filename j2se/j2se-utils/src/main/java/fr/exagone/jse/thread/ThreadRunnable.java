package fr.exagone.jse.thread;


/**
 * Cr�ation de thread en utilisant l'interfacae Runnable.
 * @author Gildas
 *
 */
public class ThreadRunnable implements Runnable{

	private int time;
	private String id;
	
	public ThreadRunnable(int time, String strId) {
		this.time = time;
		this.id = strId;
	}
	
	public void run() {
		try {
			Thread.sleep(this.time);
			System.out.println("Message du thread Runnable: " + this.id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		try {
			System.out.println("Message du programme principal");
			Thread t1 = new Thread(new ThreadRunnable(1000, "thread1"));
			Thread t2 = new Thread(new ThreadRunnable(2000, "thread2"));	
			t1.start();
			t2.start();
			Thread.sleep(3000);
			System.out.println("Message du programme principal");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}