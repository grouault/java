package fr.exagone.jse.thread;
/**
 * Classe permettant d'afficher en console un défilé des secondes.
 * @author Gildas
 */
public class ThreadSeconde extends Thread {
	
	private long init;
	
	public ThreadSeconde(long pInit) {
		this.init = pInit;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				System.out.println((System.currentTimeMillis()-this.init)/1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		long timeInit = System.currentTimeMillis();
		ThreadSeconde thread = new ThreadSeconde(timeInit);
		thread.start();
	}
	
}
