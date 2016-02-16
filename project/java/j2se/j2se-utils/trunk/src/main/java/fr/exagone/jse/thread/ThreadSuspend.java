package fr.exagone.jse.thread;

public class ThreadSuspend extends Thread {

	private boolean doWait;
	
	public ThreadSuspend() {
		this.doWait = false;
	}
	
	@Override
	public void run() {
		while (true){
			try {
				System.out.println("ThreadSupend - running");
				this.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			synchronized(this){
				while (doWait) {
					try {
						System.out.println("ThreadSuspend - suspend");
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Programme principal: début");
		ThreadSuspend thread = new ThreadSuspend();
		thread.start();
		System.out.println("Programme principal: message1");
		
		while (true) {
			
			//arrêt du thread
			System.out.println("Programme principal: arrêt du thread");
			synchronized(thread){
				thread.setDoWait(true);
			}
			System.out.println("Programme principal: message2");
			
			//Pose de 2secondes
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			//redémarrage du thread
			System.out.println("redémmarage du thread");
			synchronized(thread){
				thread.setDoWait(false);
				thread.notify();
			}
			
			//attente du process
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public boolean isDoWait() {
		return doWait;
	}

	public void setDoWait(boolean doWait) {
		this.doWait = doWait;
	}
	
}
