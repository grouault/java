package fr.exagone.jse.io.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * Client qui lit dans un buffer un fichier.
 * 
 * 
 * @author grouault
 *
 */
public class Client2 {
  
  public static final String SERVER = "127.0.0.1";
  public static final int SOCKET_PORT = 13267;
  
  public static final String FILE_TO_TRANSFERT = "C:\\tmp\\socket\\in\\file_in.pdf";
  public static final int FILE_TO_SAVE_SIZE = 60223860;
  
  public static void main(String[] args) throws IOException {
    
    
    int bytesRead;
      
    FileInputStream fis = null;
    OutputStream enSortie = null;
      
    Socket socketServeur = null;
  
    try {
      
      // lecture du fichier dans un buffer.
      File f = new File(FILE_TO_TRANSFERT);
      fis = new FileInputStream(f);
      
      
      System.out.println("Longueur du fichier = " + f.length());
      
      byte[] tabByteArray = new byte[(int)f.length()];

      
      
      // transfert du fichier au serveur
      // ecriture en sortie du buffer
      socketServeur = new Socket(SERVER, SOCKET_PORT);
      enSortie = socketServeur.getOutputStream();
      
      int r;
      while ((r=fis.read(tabByteArray))>=0) {
        System.out.println("Nombre d'octets lu dans le buffer = " + r);
        enSortie.write(tabByteArray, 0, r);
      }
      enSortie.flush();
      
    } catch (FileNotFoundException e) {
    
      System.out.println("FileNotFoundException : ex = " + e.getMessage());
      e.printStackTrace();

    } catch (IOException e) {
      
      System.out.println("IOException : ex = " + e.getMessage());
      e.printStackTrace();
    
    } finally {
    
      if (enSortie != null) enSortie.close();
      if (socketServeur != null) socketServeur.close();
      
    }
    
  }
      
}
