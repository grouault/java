package fr.exagone.jse.io.socket;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

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
		  
		    // lire le fichier en entree.
		    // recuperation du fichier dans le tablau de byte.
		    InputStream is = socketClient.getInputStream();
		    byte[] tabByteArray = new byte[FILE_TO_SAVE_SIZE];
		    bytesRead = is.read(tabByteArray, 0, tabByteArray.length);
		    current = bytesRead;
		    System.out.println("SERVER : current = " + current);
		    int i = 0;
		    do {
		      bytesRead = is.read(tabByteArray, current, (tabByteArray.length-current));
		      if (bytesRead >= 0) {
		        current += bytesRead;
		      }
		      System.out.println("Server: bytesRead = " + bytesRead);
		      System.out.println("Server: current = " + current);
		      System.out.println("------" + i++);
		    } while(bytesRead > -1);

		    // ecrire le fichier en local.
        fos = new FileOutputStream(FILE_TO_SAVE);
        bos = new BufferedOutputStream(fos);

        bos.write(tabByteArray, 0, current);
        bos.flush();
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
