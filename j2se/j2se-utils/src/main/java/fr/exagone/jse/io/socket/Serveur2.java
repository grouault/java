package fr.exagone.jse.io.socket;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur2 {

  public static final int SOCKER_PORT = 13267;
  
  public static final String FILE_TO_SAVE = "C:\\tmp\\socket\\out\\file_out.pdf";
  public static final int FILE_TO_SAVE_SIZE = 60223860;
  
  public static void main(String[] args) throws IOException {
    
    ServerSocket serveur = null;
    Socket socketClient = null;
    
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    
    try {
      
      serveur = new ServerSocket(SOCKER_PORT);
      while(true) {
        
        System.out.println("waiting...");
        socketClient = serveur.accept();

        // ecrire le fichier en local.
        fos = new FileOutputStream(FILE_TO_SAVE);
        bos = new BufferedOutputStream(fos);
        
        // lire le fichier en entree.
        // recuperation du fichier dans le tablau de byte.
        InputStream is = socketClient.getInputStream();
        byte[] tabByteArray = new byte[FILE_TO_SAVE_SIZE];
        bytesRead = is.read(tabByteArray, 0, tabByteArray.length);
        System.out.println("SERVER : current = " + current);
       
        int i = 0;
        do {

          if (bytesRead >= 0) {
            // on lit dans le buffer:
            // - a partir du byte : current
            // - le nombre d'octetes lus : bytesRead 
            bos.write(tabByteArray, current, bytesRead);
            bos.flush();
            current += bytesRead; 
          }
          
          // on lit des donnees de l'input stream dans un buffer
          // - current : offset de depart du buffer
          // - nombre maximum d'octets à lire
          bytesRead = is.read(tabByteArray, current, (tabByteArray.length-current));

          // DEBUG
          System.out.println("Server2: current = " + current);
          System.out.println("Server2: bytesRead = " + bytesRead);
          System.out.println("Server2: current + bytesRead = " +  (current + bytesRead));
          System.out.println("------" + i++);
        
        } while(bytesRead > -1);

        System.out.println("File : " + FILE_TO_SAVE + " recus (" + current + " bytes read)");
        
        bos.close();
        fos.close();
        socketClient.close();
        
      }
      
    } finally {
      
      if (fos != null) 
         fos.close();
      
      if (bos != null) 
        bos.close();
      
      if (socketClient != null) 
        socketClient.close();
      
      if (serveur != null)
        serveur.close();
    
    }
    

    
  }
  
}
