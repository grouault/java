package eni.jse.tp.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Accès à une structure tableau stockée physiquement.
 * L'accès au tableau est assuré par la méthode RandomAccessFile
 * @author Gildas
 *
 */
public class FicTableau {

	private int taille;
	private File fichier;
	
	/**
	 * constructeur
	 * @param size
	 * @param file
	 */
	public FicTableau(File file,int size){
		taille = size;
		fichier = file;
	}
	
	
	/**
	 * @param indice
	 * @param value
	 */
	public void ecrire(int indice, int value){
		RandomAccessFile raf = null;
		try {
			if (indice<taille) {
				raf = new RandomAccessFile(fichier,"rw");
				//On met donc à jour l'indice: indice * 4 (un entier est codé sur 4 octets)
				//on se positionne à l'indice
				raf.seek(indice*4);
				raf.writeInt(value);
			} else {
				throw new ArrayIndexOutOfBoundsException("L'indice d'écriture " + indice + " est trop grand!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			try {
				if(raf!=null){
					raf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Permet de lire une valeur du tableau à l'indice donné.
	 * @param indice
	 */
	public int lire(int indice){
		RandomAccessFile raf = null;
		int val = -1;
		try {
			if (indice>0 && indice<taille) {
				raf = new RandomAccessFile(fichier, "r");
				//on lit un entier codé sur 4 octets - on met à jour l'indice.
				raf.seek(indice*4);
				return raf.readInt();
			} else {
				throw new ArrayIndexOutOfBoundsException("L'indice de lecture " +  indice + " est trop grand!");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
//			EOFException: indice pour lequel aucune valeur n'est défini
			val = 0;
		} 
		catch (Exception e) {
			//indice trop grand ou trop petit.
			System.out.println(e.getMessage());
		} finally{
			try {
				if(raf!=null){
					raf.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return val;		
	}
	
	/**
	 * test
	 * @param args
	 */
	public static void main(String [] args){
		String filePath = "d:/devs/tp-eni-jse/io/FicTableau.dat";
		File objFichier = new File(filePath);
		
		//creation du fichier s il n existe pas.
		if(!objFichier.exists()){
			try {
				objFichier.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		Création du tableau
		FicTableau tabTest = new FicTableau(objFichier,50);
//		operation d'écriture
		tabTest.ecrire(55, 30);
//		operations de lecture
		System.out.printf("Valeur du tableau à l'indice %d : %d\n", 3, tabTest.lire(3));
		System.out.printf("Valeur du tableau à l'indice %d : %d\n", 23, tabTest.lire(23));
		System.out.printf("Valeur du tableau à l'indice %d : %d\n", 55, tabTest.lire(55));
		

	}
	
}