package eni.jse.tp.thread.mutli;

/**
 * Test du caclulateur avec les classes du package.
 * @author Gildas
 *
 */
public class TestCalculateur extends Thread{
	
	private int cpt;
	
	public TestCalculateur(int pCpt) {
		this.cpt = pCpt;
	}
	
	public synchronized void run() {
		Calculateur calculateur = new Calculateur();
		String strNomP = null;
		System.out.println("debut main-test");
		for (int i = 0; i < 10; i++) {
			this.cpt--;
			strNomP = "Personne " + i;
			Personne p = new Personne(strNomP,calculateur);
			p.start();
			System.out.println(i);
		}
		try {
			synchronized (calculateur) {
				System.out.println("TestCalculateur: en attente sur calculateur calcul.");
				calculateur.wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TestCalculateur: notify");
		this.notify();
	}

	public synchronized void attendreTerminaison() {
		try {
			if (this.cpt>0) {
				System.out.println("TestCalculateur en attente sur TestCalculateur: run");
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("fin main-test");
	}
		
		
	
	public static void main(String[] args) {
		TestCalculateur test = new TestCalculateur(10);
		test.start();
		test.attendreTerminaison();
	}
}
