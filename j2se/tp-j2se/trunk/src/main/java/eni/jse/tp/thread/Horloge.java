package eni.jse.tp.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Horloge extends Thread {

	private Vector lstAbonnes;
	private long timeInit;
	
	public Horloge() {
		this.lstAbonnes = new Vector<Abonne>();
		timeInit = System.currentTimeMillis(); //temps initial pour l'horloge	
	}
	
	/**
	 * ajoute un abonne dans la liste des abonnées
	 * @param objAbonne
	 * @param timeAbonnement
	 */
	public synchronized void ajouterAbonne(Abonne objAbonne, int timeAbonnement) {
		this.lstAbonnes.add(objAbonne);
		this.lstAbonnes.add(timeAbonnement);
	}
	
	@Override
	public synchronized void run() {
		System.out.println("run");
		while(!this.lstAbonnes.isEmpty()){	
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long seconde = (System.currentTimeMillis()-this.timeInit)/1000;
			
			for (int i = 0; i <lstAbonnes.size(); i+=2) {
				Abonne currentAbonne = (Abonne)lstAbonnes.get(i);
				long currentTimeAbonne = ((Integer)lstAbonnes.get(i+1)).intValue();
				
				if(seconde>currentTimeAbonne){
					currentAbonne.run();
					lstAbonnes.remove(i);
					lstAbonnes.remove(i);
				}
				
			}
		}
		this.notify();
		
	}
	
	
	public synchronized void attendreTerminaison(){
		if(this.lstAbonnes.size()>0){
			try {
				System.out.println("wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Horloge horloge = new Horloge();
		
		for (int i = 0; i < 3; i++) {
			Abonne currentAbonne = new Abonne("Abonne"+i);
			horloge.ajouterAbonne(currentAbonne, i*3);
		}
		
		horloge.start();
		horloge.attendreTerminaison();
		System.out.println("Fin de traitement");
		//horloge.attendreTerminaison();
	}
}

/**
 * classe abonne: thread
 * @author Gildas
 *
 */
class Abonne implements Runnable{

	private String strMessage;
	
	public Abonne(String pMessage) {
		this.strMessage = "Message: " + pMessage;
	}
	
	@Override
	public void run() {
		System.out.println(this.strMessage);
		
	}

	public String getStrMessage() {
		return strMessage;
	}

	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}
	
}
