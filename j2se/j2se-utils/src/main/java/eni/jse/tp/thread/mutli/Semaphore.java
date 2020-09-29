package eni.jse.tp.thread.mutli;

public class Semaphore {

	/* nombre d'accès simultané à une ressource */
	private int limite;
	
	public Semaphore(int pLimite) {
		this.limite = pLimite;
	}

	/* permet de prendre un jeton au niveau du semaphore */
	public synchronized void acceder(String pThread) {
		limite--;
		if (limite<0) {
			try {
				System.out.println(pThread + ": en attente.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	/* permet de rendre un jeton au nivau du sémaphore */
	public synchronized void liberer(String pThread) {
		limite++;
		if (limite<=0) {
			//des threads sont en attente
			System.out.println(pThread + ": libéré.");
			notify();
		}
	}
	
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	
	
}
