package fr.exagone.jse.io.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

/**
 * 
 * Client qui lit dans un buffer un fichier.
 * 
 * 
 * @author grouault
 *
 */
public class Client {
	
  public static final String SERVER = "127.0.0.1";
  public static final int SOCKET_PORT = 13267;
  
  public static final String FILE_TO_TRANSFERT = "C:\\tmp\\socket\\in\\file_in.pdf";
  public static final int FILE_TO_SAVE_SIZE = 60223860;
  
  public static void main(String[] args) throws IOException {
    
    
    int bytesRead;
      
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    BufferedOutputStream enSortie = null;
      
    Socket socketServeur = null;
  
    try {
      
      // lecture du fichier dans un buffer.
      fis = new FileInputStream(FILE_TO_TRANSFERT);
      bis = new BufferedInputStream(fis);
      
      int nByte = bis.available();
      
      System.out.println("Longueur du fichier = " + nByte);
      
      byte[] tabByteArray = new byte[nByte];
      bytesRead = bis.read(tabByteArray, 0, tabByteArray.length);
      System.out.println("Nombre d'octets lu dans le buffer = " + bytesRead);
      
      // transfert du fichier au serveur
      // ecriture en sortie du buffer
      socketServeur = new Socket(SERVER, SOCKET_PORT);
      enSortie = new BufferedOutputStream(socketServeur.getOutputStream());
      enSortie.write(tabByteArray, 0, tabByteArray.length);
      enSortie.flush();
      
    } catch (FileNotFoundException e) {
    
      System.out.println("FileNotFoundException : ex = " + e.getMessage());
      e.printStackTrace();

    } catch (IOException e) {
      
      System.out.println("IOException : ex = " + e.getMessage());
      e.printStackTrace();
    
    } finally {
    
      if (fis != null) bis.close();
      if (bis != null) fis.close();
      if (enSortie != null) enSortie.close();
      if (socketServeur != null) socketServeur.close();
      
    }
    
  }
			
}
