package eni.jse.tp.io.serialisation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseDeDonnees {

	private String fichierStockage;
	private List<Personne> db;
	
	public BaseDeDonnees(String fichierStockage) {
		this.fichierStockage = fichierStockage;
	}
	
	public void ajouterPersonne(Personne objPersonne) throws BaseDeDonneesException{
		this.majBaseMemoire();
		OutputStream os = null;
		try {
			//ajout de la personne dans la liste.
			db.add(objPersonne);
			
			//sauvegarde de la liste.
			os = new FileOutputStream(fichierStockage);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(db);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new BaseDeDonneesException(e,fichierStockage);
		} finally{
			if (os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}	
	}
	
	/**
	 * Initialisation de la liste db.
	 */
	@SuppressWarnings("unchecked")
	public void majBaseMemoire() throws BaseDeDonneesException{
		File file = new File(fichierStockage);
		if (file.exists()){
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(is);
				db = (List<Personne>) ois.readObject();
				
			} catch (FileNotFoundException e) {
				throw new BaseDeDonneesException(e, fichierStockage);
			} catch (IOException e) {
				throw new BaseDeDonneesException(e, fichierStockage);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally{
				if (is!=null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else{
			//on crée une liste vide.
			db = new ArrayList<Personne>();
		}
	}
	
	public List<Personne> chercher(String nom) throws BaseDeDonneesException{
		majBaseMemoire();
		List<Personne> listCourante = new ArrayList<Personne>();
		for (Iterator<Personne> iterator = db.iterator(); iterator.hasNext();) {
			Personne objPersonneCourante = (Personne) iterator.next();
			if(objPersonneCourante.getNom().equals(nom)){
				listCourante.add(objPersonneCourante);
			}
		}
		return listCourante;
	}
	
	public static void main(String[] args) {
		try{
			String strPathBD  = "d:/devs/tp-eni-jse/io/bd/bd.dat";
			BaseDeDonnees bd = new BaseDeDonnees(strPathBD);
			Personne personne1 = new Personne("Guérin","Aurelie");
			bd.ajouterPersonne(personne1);
			
			List<Personne> listeRecherche = bd.chercher("Rouault");
			if (!listeRecherche.isEmpty()) {
				int cpt = 0;
				for (Iterator<Personne> iterator = listeRecherche.iterator(); iterator
						.hasNext();) {
					Personne p = (Personne) iterator.next();
					System.out.println(p.toString());
					cpt ++;
				}
				System.out.println("Nombres de Personnes:" + cpt);
			}
		} catch (BaseDeDonneesException e) {
			e.printStackTrace();
		}
	}
}
