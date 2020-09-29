package eni.jse.tp.io.serialisation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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

public class BaseDeDonnees2 {

	private String fichierStockage;
	
	public BaseDeDonnees2(String fichierStockage) {
		this.fichierStockage = fichierStockage;
	}
	
	public void ajouterPersonne(Personne objPers) throws BaseDeDonneesException{
		
		OutputStream os = null;
		ByteArrayOutputStream bos = null;
		
		try {
			os = new FileOutputStream(fichierStockage, true);
			bos = new ByteArrayOutputStream();//Flux mémoire
			//Sérialisation l'objet dans le flux mémoire
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(objPers);
			
			//récupération de la taille de l'objet et de l'objet sous la forme d'un tableau d'octets.
			int size = bos.size();
			byte[]tabObj = bos.toByteArray();
			
			//écriture de l'objet dans le fichier.
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeInt(size);
			dos.write(tabObj);
			
		} catch (FileNotFoundException e) {
			throw new BaseDeDonneesException(e, fichierStockage);
		} catch (IOException e) {
			throw new BaseDeDonneesException(e, fichierStockage);
		} finally{
			try {
				if(os!=null){
					os.close();
				}
				if(bos!=null){
					bos.close();
				}
			} catch (IOException e) {
				throw new BaseDeDonneesException(e, fichierStockage);
			}
			
		}
	}
	
	public List<Personne> chercher(String nom) throws BaseDeDonneesException{
		List<Personne> listePersonne = new ArrayList<Personne>();
		InputStream is = null;
		ByteArrayInputStream bis = null;
		
		try {
			is = new FileInputStream(fichierStockage);
			DataInputStream dis = new DataInputStream(is);
			ObjectInputStream ois = null;
			
			try{
				while(true){
					//recuperation de l'objet: flux d'octet
					int size = dis.readInt();
					byte[] tab = new byte[size];
					dis.read(tab);
					
					//deserialisation du flot d'octet
					bis = new ByteArrayInputStream(tab);
					ois = new ObjectInputStream(bis);
					Personne p = (Personne)ois.readObject();
					
					//ajout de la personne dans la liste
					if (p.getNom().equals(nom)) {
						listePersonne.add(p);
					}
					
				}
			}catch (EOFException e) {
			
			} catch (IOException e) {
				throw new BaseDeDonneesException(e, fichierStockage);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			throw new BaseDeDonneesException(e, fichierStockage);
		} finally{
			try {
				if (is!=null) {
					is.close();
				}
				if (bis!=null) {
					bis.close();
				}
			} catch (IOException e) {
				throw new BaseDeDonneesException(e, fichierStockage);
			}
			
		}
		return listePersonne;
	}
	
	public static void main(String[] args) {
		try{
			String strPathBD  = "d:/devs/tp-eni-jse/io/bd/bd2.dat";
			BaseDeDonnees2 bd = new BaseDeDonnees2(strPathBD);
			Personne p = new Personne("Guérin","Aurélie");
			bd.ajouterPersonne(p);
			System.out.println("Personne ajoutée: " + p.toString());
			
			//lire le contenu de la base de données2
			List<Personne> liste = bd.chercher("Guérin");
			if (!liste.isEmpty()) {
				for (Iterator<Personne> iterator = liste.iterator(); iterator.hasNext();) {
					Personne objPers = (Personne) iterator.next();
					System.out.println(objPers.toString());
				}
			}
		} catch (BaseDeDonneesException e) {
			e.printStackTrace();
		}
	}
}
