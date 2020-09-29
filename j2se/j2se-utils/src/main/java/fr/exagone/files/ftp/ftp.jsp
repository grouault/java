<%@ page pageEncoding="UTF-8" contentType="charset=UTF-8"

%><%@page import="java.util.*,

                          java.io.File,

                          java.text.DateFormat,

                          java.text.SimpleDateFormat,

                          java.io.IOException,

                          java.io.FileInputStream,

                          java.io.FileOutputStream,

                          java.io.ByteArrayInputStream,

                          java.lang.NumberFormatException,

                          java.io.FilenameFilter"

%><%@page import="org.apache.commons.fileupload.FileItem,

                        org.apache.commons.fileupload.FileItemFactory,

                        org.apache.commons.fileupload.servlet.ServletFileUpload,

                        org.apache.commons.fileupload.disk.DiskFileItemFactory,

                        org.apache.commons.fileupload.FileItemFactory,

                        org.apache.commons.fileupload.FileUploadException,

                        org.apache.commons.net.ftp.FTPClient,

                        org.apache.commons.net.ftp.FTP,

                        org.apache.commons.net.ftp.FTPFile,

                        java.util.zip.ZipEntry,

                        java.util.zip.ZipInputStream"%>

                 

<%--

 

CHANGELOG

20100901 GR Creation

     

PRINCIPE                    

Ce fichier permet de recuperer les fichiers uploader pour faire fonctionner le flash.

Ces fichiers sont a copier sur le serveur.

 

INPUT

1     :     extension du media (swf pour le fichier primaire)

2     :     nom du repertoire de stockage sur akamai

3     :     taille limite des fichier a uploader sur akamai                       

--%>

 

<%------------------------------------------------%>

<%-- Initialisation des parametres de conf postes --%>

<%------------------------------------------------%>

<%

String pathUploadFlash = null;/* conf folder pour les fichiers temporaires sur le serveur */

String fileExtension = null;

String flashFolderServer = null;

String fileSizeLimit = null;

String oldValueAttribute = null;

String newValueAttribute = "";

String errorMsg = null;

/*

String strFtpConnect = "webprep.dmzprive.cite-sciences.fr";

String strFtpLogin = "sactu";

String strFtpMdp = "sactu";

*/

String strFtpConnect = "webdev.citepro.cite-sciences.fr";

String strFtpLogin = "reservoir-offre";

String strFtpMdp = "reservoir-offre";

FTPClient ftp = null;

FileInputStream fis = null;

ByteArrayInputStream byteArrayInputStream = null;

%>

 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>UploadSaAnimationAction</title>

 

      <style type="text/css">

      <!--

      .error{

      color: red;

      }    

      .important{

      color: red;

      }    

      .upload-ok{

      color: #00CC00;

      }

      .small-text{

      font-family:Verdana,Geneva,Arial,Helvetica,Swiss,SunSans-Regular;

      font-size:11px;

      }

      -->

      </style>

 

</head>

<body>

 

<%--

      recuperation des donnees du formulaire:

            - data fichier

            - data non fichier

--%>

 

<%

      System.out.println("[DEBUG GR] - UploadSA_Animation - DEBUT");

      boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

      FileItemFactory factory = new DiskFileItemFactory();

      ServletFileUpload upload = new ServletFileUpload(factory);

     

      if (!isMultipartContent) {

            out.println("You are not trying to upload<br/>");

            return;

      }    

     

      try {

            List<FileItem> fields = upload.parseRequest(request);

            Iterator<FileItem> it = fields.iterator();

            if (!it.hasNext()) {

                  out.println("Aucun fichier en upload!");

            }

            else{

                  //

                  //recuperation et traitement des parametres

                  //

                  //creation d un timstamp pour le nom du repetoire de stockage.

                  Date currentDate = new Date();

                  DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

                  String cDate = dateFormat.format(currentDate);

                  // initialisation du ftp

                  try{

                  System.out.println("---> JN CONNEXION FTP");  

                        ftp = new FTPClient();

                        /* connexion au serveur ftp */

                        ftp.connect(strFtpConnect);

                             System.out.println("---> JN CONNEXION FTP 2");

                        if(!ftp.login(strFtpLogin, strFtpMdp)) {

                             throw new Exception("Erreur de connexion au serveur FTP, verifier le login et le mdp.");

                        }else{

                                   System.out.println("CONNEXION OK");

                                        

                        }

                       

                        System.out.println("---> JN CONNEXION FTP 3");

                        //parametrage du transfert: important

                        ftp.enterLocalPassiveMode();

                        ftp.setFileTransferMode( FTPClient.BINARY_FILE_TYPE );

                        ftp.setFileType(FTP.BINARY_FILE_TYPE);

                       

                        String strFolderStockage = null;

                        File fileFolderStockage = null;

                        while (it.hasNext()) {

                            

                             boolean bCreateFile = true;

                             FileItem fileItem = it.next();

                             boolean isFormField = fileItem.isFormField();

                             if (!isFormField ) {

                                    //Traitement des attributs de type fichier

                                   String fileName = fileItem.getName();

                                   if(fileName!=null && !"".equals(fileName)){

                                        

                                         strFolderStockage = pathUploadFlash + flashFolderServer + cDate + "/";

                                         //System.out.println("folderStockage = " + strFolderStockage);

                                         //System.out.println("fileName = " + fileName);

                                         //mise a jour de la valeur stocke dans l attribut UrlFlash

                                         if(fileItem.getFieldName()!=null && "mediaUpload-0".equals(fileItem.getFieldName())){

                                               newValueAttribute = flashFolderServer + dateFormat.format(currentDate) + "/" + fileName;

                                               bCreateFile = isFileExtensionValid(fileName, fileExtension);

                                         }                      

 

                                   //creation du folder si ce dernier n existe pas.

                                   // etape 1 le folder local

                                   // etape 2 folder distant

                                   fileFolderStockage = new File(strFolderStockage);

                                  

                                   if(!fileFolderStockage.exists()){

                                         boolean folderExists = fileFolderStockage.mkdir();

                                        

                                         if(!folderExists){

                                               errorMsg = "Impossible de creer le fichier " + strFolderStockage;

                                               System.out.println("-- Error : UploadSaAnimationAction --");

                                               System.out.println(errorMsg);

                                               System.out.println("-------------------------------------");   

                                         }

                                        

                                         // creation du folder distant

                                        

                                         if(!ftp.makeDirectory(cDate)){

                                              errorMsg = "Impossible de creer le folder distant  " + cDate ;

                                               System.out.println("-- Error : UploadSaAnimationAction --");

                                               System.out.println(errorMsg);

                                               System.out.println("-------------------------------------");

                                               bCreateFile = false;

                                         }else{

                                               System.out.println("FTP folder cree "+ cDate);

                                         }

                                   }

                                                    

                                   System.out.println("[DEBUG GR] - folder stockage : " + strFolderStockage);

                                   if(fileFolderStockage.exists() && bCreateFile){

 

                                         //copie locale du fichier sur le serveur.     

                                         File uploadedFile = new File(strFolderStockage + fileName);

                                         try{

                                               fileItem.write(uploadedFile);

                                         }

                                         catch(Exception e){

                                               errorMsg = "Impossible d uploader le fichier " + e.getMessage();

                                               System.out.println("-- Error : UploadSaAnimationAction --");

                                               System.out.println(errorMsg);

                                               System.out.println("-------------------------------------");

                                         }

                                        

                                         if (isZipExtension(fileName)) {

                                              

                                               // TRAITEMENT des zips.

                                               System.out.println("[DEBUG GR] - DEBUT TRAITEMENT FICHIER ZIP");

                                               processUnZip(ftp, strFolderStockage, fileName, cDate);

                                               System.out.println("[DEBUG GR] - FIN TRAITEMENT FICHIER ZIP TODO");

                                              

                                         } else {

 

                                               // TRANSFERT DU FICHIER FTP.

                                               System.out.println("FTP Transfert de fichier------------");

                                               fis = new FileInputStream(uploadedFile);

                                               byte[] tabByte = new byte[(int)uploadedFile.length()];

                                               int nbByte = fis.read(tabByte);

                                               //lecture du flux memoire.

                                               byteArrayInputStream = new ByteArrayInputStream(tabByte);

                                              

                                               //put sur le serveur webhost

                                               System.out.println("uploadedFile.getName()"+uploadedFile.getName());

                                             ftp.changeWorkingDirectory(cDate);

 

                                               if(!ftp.storeFile(uploadedFile.getName(), byteArrayInputStream)){

                                                     errorMsg = "Impossible d uploader le fichier " + uploadedFile.getName() ;

                                                     System.out.println("-- Error : UploadSaAnimationAction --");

                                                     System.out.println(errorMsg);

                                                     System.out.println("-------------------------------------");

                                               }    

                                              

                                         }

                                  

                                   }

                             }

                        }

                        else{

                             //Traitement des attributs autres que fichier

                            String name = fileItem.getFieldName();

                            String value = fileItem.getString();

                            if(name!=null && !"".equals(name) && !"Submit".equals(name)){

                              try{

                                    int i = Integer.parseInt(name);

                                   switch(i){

                                         case 1:

                                               fileExtension = value;

                                               break;

                                         case 2:

                                               flashFolderServer = value;

                                                break;

                                         case 3:

                                               fileSizeLimit = value;

                                               break;

                                         case 4:

                                               oldValueAttribute = value;

                                               break;

                                         case 5:

                                               pathUploadFlash = value;

                                   }

                              } catch(NumberFormatException e){ 

                                         e.printStackTrace();

                              }

                            }                  

                        }

                  }//while

                       

                  // suppresion locale du fichier qui a servi pour le transfert FTP.

                  if (fileFolderStockage != null && fileFolderStockage.isDirectory()) {

                        System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - DEBUT de la tache de suppression du folder temporaire]");

                        if (strFolderStockage != null && deleteLocalTree((pathUploadFlash + flashFolderServer), cDate)) {

                        }

                        System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - FIN de la tache de suppression du folder temporaire]");                   

                  }

                 

                       

                  // suppression du fichier si on est en modification.      

                  if (oldValueAttribute != null) {        

                        // folder to delete.

                        String folderToDelete = extractFolderTimeStamp(oldValueAttribute);

                        // String pathToDelete = pathUploadFlash + folderToDelete;

                        String pathToDelete = "/" + folderToDelete;

                        String racine = "/";

                                        

                        // suppression ftp.

                        if (ftp.changeWorkingDirectory(racine)){

                            

                             //recuperation de fichier

                             FTPFile[] files = ftp.listFiles(".");

                             if(files.length>0){

                                   System.out.println("[FTP-Upload-SaAnimation] - [Modification] - DEBUT de la tache de suppression de l ancien REMOTE-FOLDER]");

                                   for (int i = 0; i< files.length; i++) {

                                         FTPFile currentFile = files[i];

                                         if (currentFile.getName() != null && currentFile.getName().equals(folderToDelete)) {

                                               if (currentFile.isDirectory()) {

                                                     if (deleteFTPTree(ftp, racine, currentFile.getName()) ){

                                                           oldValueAttribute = null;

                                                           System.out.println("[FTP-Upload-SaAnimation] - [DeleteDirectory en succes] " + currentFile.getName());

                                                     }

                                               }

                                         }

                                   }

                                   System.out.println("[FTP-Upload-SaAnimation] - [Modification] - FIN de la tache de suppressio de l ancien REMOTE-FOLDER]");

                             }

                             else{

                                   System.out.println("[FTP-Upload-SaAnimation] - Aucun fichier à récupérer!");

                             }

                            

                        } else {

                             System.out.println("[FTP-Upload-SaAnimation] - Impossible de deplacer dans le folder : " + pathToDelete);

                        }

                                              

                  }    

                 

            ftp.logout();

    } catch(IOException e) {

     

      e.printStackTrace();

    } finally {

      if(ftp.isConnected()) {

        try {

          ftp.disconnect();

        } catch(IOException ioe) {

          // do nothing

        }

      }   

    }

 

                       

                  /*

                  * METTRE a jour l attribut url du media du formulaire de contribution.

                  */

                  %>               

                  <%-- Message succes --%>

                  <span class="small-text">

                        <%

                        if(errorMsg!=null && !"".equals(errorMsg)){%>

                             <span class="important">Erreur lors du traitement : <%= errorMsg %></span>

                        <%   

                        }

                        else{%>

                             <script language="java-script">

                                   window.parent.document.AppForm.Attribute_UrlFlashSaAnimation.value = '<%= newValueAttribute %>';

                             </script>                   

                       

                             Le(s) fichier(s) sont upload&eacute;s.<br/>

                             <span class="important">Vous devez enregistrer le contenu!</span>

                        <%   

                        }

                        %>                     

                  </span>

                 

                  <%

                 

           

            }

           

      } catch (FileUploadException e) {

            e.printStackTrace();

      } catch (Exception e){

            e.printStackTrace();

            errorMsg = "Une erreur innattendue est survenue. Contacter votre administrateur!"; %>

            <span class="error"><%= errorMsg %></span><%

      }

%>

 

 

 

     

</body>

</html><%!

private String extractFolderTimeStamp (final String path) {

      String timeStampFolder = null;

      int firstIndex = path.indexOf("/") + 1;

      int lastIndex = path.lastIndexOf("/");

      timeStampFolder = path.substring(firstIndex, lastIndex);

      return timeStampFolder;

}

 

/**

* deleteFTPTree : permet de supprimer un dossier et son contenu via le ftpClien.

* @param: ftpClient

* @param: pathParent : chemin ftp d acces au folder.

* @param: folderToDelete : folger a supprimer

* @author: GR(xwan)

*/

private boolean deleteFTPTree(final FTPClient ftpClient, final String pathParent, final String folderToDelete) throws IOException {

      boolean bDelete = false;

      List<String> foldersNotAuthorized = new ArrayList<String>();

      foldersNotAuthorized.add(".");

      foldersNotAuthorized.add("..");

 

      // on se postionne dans le remote-folder.

      String pathDirectory = pathParent + folderToDelete;

      if (ftpClient.changeWorkingDirectory(pathDirectory)){

           

            // 1- Suppression du contenu du repertoire.

            FTPFile[] files = ftpClient.listFiles(".");

            if(files.length>0){

                  for (int i = 0; i< files.length; i++) {

                        FTPFile currentFile = files[i];

                        if (currentFile.isDirectory()) {

                             if ( currentFile.getName() != null && !foldersNotAuthorized.contains(currentFile.getName()) ) {

                                   System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - Traitement du sous-repertoire " + currentFile.getName());

                                   // appelle de la methode deleteTree sur ce repertoire.

                                   String racine = pathDirectory + "/";

                                   deleteFTPTree (ftpClient, racine, currentFile.getName());

                             }

                        } else {

                             // suppression du fichier.

                             if (!ftpClient.deleteFile(currentFile.getName())){

                                   System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - Suppression du fichier - " + currentFile.getName() + " - [KO]");

                             } else {

                                   System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - Suppression du fichier - " + currentFile.getName() + " - [OK]");

                             }

                        }

                  }

            }

 

            // 2- Suppression du repertoire vide.

            if (ftpClient.changeWorkingDirectory(pathParent)){

                  if (!ftpClient.removeDirectory(folderToDelete)) {

                        System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - Suppression du repertoire - " + folderToDelete + " - [KO]");

                  } else {

                        System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - Suppression du repertoire - " + folderToDelete + " - [OK]");

                        bDelete = true;

                  }

            } else {

                  System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - [ERROR - Suppression du repertoire racine] - Impossible d acceder au repertoire " + pathDirectory);

            }

     

     

      } else {

            System.out.println("[FTP-Upload-SaAnimation] - [deleteFTPTree] - Impossible d acceder au repertoire " + pathDirectory);

      }

 

      return bDelete;

}

 

/**

* deleteLocalTree : permet de supprimer le fichier temporaire de stockage sur le serveur.

* @pathDirectory : chemin d acces au repertoire

* @directoryName : nom du repertoire

*/

private boolean deleteLocalTree(final String pathDirectory, final String directoryName) throws IOException{

     

      File dirToDelete = new File(pathDirectory + directoryName);

      if (dirToDelete.exists()) {       

            // 1- Suppression du contenu du folder.

            File[] files = dirToDelete.listFiles();

            if (files.length > 0) {

                  for (int i =0; i<files.length; i++) {

                        File fileToDelete = files[i];

                        if (!fileToDelete.isDirectory()) {

                             // suppression des fichiers.

                             if (!fileToDelete.delete()) {

                                   System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - Suppression du fichier - [KO] - " + fileToDelete.getName());

                             } else {

                                   System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - Suppression du fichier - [OK] - " + fileToDelete.getName());

                             }                           

                        } else {

                             // suppression des folders.             

                             String racine = pathDirectory + directoryName + "/";

                             deleteLocalTree(racine, fileToDelete.getName());

                        }

                  }

            }

            // 2- Suppression du folder qui est normalemetn vide a ce stade.

            if (!dirToDelete.delete()) {

                  System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - Suppression du repertoire - [KO] - " + dirToDelete.getName());

            } else {

                  System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - Suppression du repertoire - [OK] - " + dirToDelete.getName());

                  return true;

            }

           

      } else {

            System.out.println("[FTP-Upload-SaAnimation] - [deleteLocalTree] - Repertoire non existant - " + dirToDelete.getAbsolutePath());

      }    

      return false;

}

 

/**

* permet de checker que l'extension est valide.

* @param fileName

* @param extensions

* @return

*/

private boolean isFileExtensionValid (final String fileName, final String extensions) {

      List<String> extensionsValid = Arrays.asList(extensions.split("#"));

      for (Iterator<String> iter = extensionsValid.iterator(); iter.hasNext();) {

            if (fileName.toLowerCase().indexOf(".".concat((String) iter.next())) > -1) {

                  return true;

            }

      }

      return false;

}

 

/**

* permet de checker qu'une extension est valide.

* @param fileName

* @return

*/

private boolean isZipExtension (final String fileName) {

      return fileName.toLowerCase().endsWith(".zip");

}

 

/**

* - Lance le process de dezippage

* - Verifie la validite du fichier a dezipper

* - Lance le process de transfert ftp

* - Lance le process de suppression du fichier temporaire.

* @param pathFolder : chemin d acces au fichier.

* @param fileNameZip : nom du fichier zip.

* @param remoteFolderDate : folder de stockage ftp

*/

private boolean processUnZip(final FTPClient ftpClient, final String pathFolder, final String fileNameZip,

            final String remoteFolderDate) throws Exception{

      if (unZip(pathFolder, fileNameZip, remoteFolderDate)) {

            String zipExtension = ".zip";

            String folderName = fileNameZip.substring(0, fileNameZip.indexOf(zipExtension));

            File folderUnzip = new File(pathFolder + folderName);

            // verification de la presence SWF

            if (folderUnzip.exists() && containsSWF(folderUnzip)){

                  System.out.println("[UNZIP-Upload-SaAnimation] - Le fichier ZIP est valide");  

                  File[] files = folderUnzip.listFiles();

                  if (files.length > 0) {

                        // Transfert des fichiers du zip sur le serveur.

                        System.out.println("[UNZIP-Upload-SaAnimation] - DEBUT transfert FTP ");

                        for (int i =0; i<files.length; i++) {

                             File fileToTransfert = files[i];

                             System.out.println("[UNZIP-Upload-SaAnimation] " + fileToTransfert.getName());

                             if (!fileToTransfert.isDirectory()) {

                                  if (!ftpUpload(ftpClient, pathFolder, folderName, fileToTransfert.getName(),remoteFolderDate)){

                                        System.out.println("[UNZIP-Upload-SaAnimation] - FTP - Transfert du fichier [KO] : " + fileToTransfert.getName());

                                  }

                             }

                        }

                        System.out.println("[UNZIP-Upload-SaAnimation] - FIN transfert FTP ");

                  } else {

                        System.out.println("[UNZIP-Upload-SaAnimation] - ERROR - Aucun fichier a transferer " + folderUnzip.getName());

                  }                                       

                  return true;

            } else {

                  System.out.println("[UNZIP-Upload-SaAnimation] - Le repertoire non valide : " + folderUnzip.getCanonicalPath());    

            }

      } else {

            System.out.println("[UNZIP-Upload-SaAnimation] - [ERROR] : Fichier non dezipper.");

      }

      return false;

}

 

/**

* Permet de dezipper un fichier

* @param pathFolder : chemin d acces au fichier.

* @param fileNameZip : nom du fichier zip.

* @param remoteFolderDate : folder de stockage ftp

*/

private boolean unZip (final String pathFolder, final String fileNameZip, final String remoteFolderDate) throws Exception {

     

      byte[] buffer = new byte[1024];

      ZipInputStream zis = null;

      FileOutputStream fos = null;

      String zipExtension = ".zip";

      File folderUnzip = null; // folder de dezippage.

     

      try {

           

            // creation du folder de destockage avec le nom du fichier zip.

            folderUnzip = new File(pathFolder + fileNameZip.substring(0, fileNameZip.indexOf(zipExtension)));

            folderUnzip.mkdir();

           

            // lecture du fichier zip

            String zipFile = pathFolder + fileNameZip;

            zis = new ZipInputStream(new FileInputStream(zipFile));

           

            // recuperation de la liste des fichiers dans le zip.

      ZipEntry ze = zis.getNextEntry();

      System.out.println("[UNZIP-Upload-SaAnimation] - DEBUT");

            while (ze != null) {

            String fileName = ze.getName();

            System.out.println("[UNZIP-Upload-SaAnimation] - fileName = " + fileName);

            File newFile = new File(folderUnzip + "/" + fileName);

              System.out.println("file unzip : "+ newFile.getAbsoluteFile());

             

              // create all non exists folders

            // else you will hit FileNotFoundException for compressed folder

              if (ze.isDirectory()) {   

                  newFile.mkdirs();

              } else {

                  new File(newFile.getParent()).mkdirs();

              }

          

            // ecriture du fichier

            if (!ze.isDirectory()) {

                    fos = new FileOutputStream(newFile);

                  int len;

                  while ((len = zis.read(buffer)) > 0) {

                        fos.write(buffer, 0, len);

                  }

                  fos.close();    

            }

              ze = zis.getNextEntry();

            }   

            System.out.println("[UNZIP] - FIN - [OK]");

            return true; // fin de l opertion ok.   

      } catch (Exception ex){

            ex.printStackTrace();

      } finally {

            try {

                  if (zis != null)

                  zis.closeEntry();

                  if (fos != null)

                        fos.close();

            } catch (IOException e) {

                  e.printStackTrace();

            }

      }    

      System.out.println("[UNZIP] - FIN - [KO]");

      return false;

}

 

/**

* verifie la presence de l extension SWF.

* @return

*/

private boolean containsSWF(final File fileFolder) {

      if (fileFolder != null) {

            return fileFolder.list(new SWFFilter()).length > 0;

      }

      return false;

}

 

/**

* permet de faire le transfert FTP

* @param ftpClient

* @param pathFile

* @param fileToUpload

* @param fileName

* @param remoteFolderDate

*/

private boolean ftpUpload (final FTPClient ftpClient, final String pathFile, final String fileToUpload, final String fileName,

            final String remoteFolderDate) throws Exception {

     

      boolean upload = false;

      File uploadedFile = new File(pathFile + fileToUpload+ "/" + fileName);

     

      // TRANSFERT DU FICHIER FTP.

      FileInputStream fis = null;

      ByteArrayInputStream byteArrayInputStream = null;

     

      try {

            fis = new FileInputStream(uploadedFile);

            byte[] tabByte = new byte[(int)uploadedFile.length()];

            int nbByte = fis.read(tabByte);

            //lecture du flux memoire.

            byteArrayInputStream = new ByteArrayInputStream(tabByte);

 

            //put sur le serveur webhost

            System.out.println("uploadedFile.getName()" + uploadedFile.getName());

            ftpClient.changeWorkingDirectory(remoteFolderDate);

 

            if(!ftpClient.storeFile(uploadedFile.getName(), byteArrayInputStream)){

                  String errorMsg = "Impossible d uploader le fichier " + uploadedFile.getName() ;

                  System.out.println("-- Error : UploadSaAnimationAction --");

                  System.out.println(errorMsg);

                  System.out.println("-------------------------------------");

            } else {

                  upload = true;

            }

           

      } finally {

            try {

                  if (fis != null) {

                        fis.close();

                  }

            } catch (IOException e) {

                  e.printStackTrace();

            }    

      }

     

      return upload;

     

}

 

/**

* filtre sur les extensions ZIP.

* @author Rouault1

*

*/

class SWFFilter implements FilenameFilter {

      public String SWF_EXTENSTION  = ".swf";

      public boolean accept(File dir, String name) {

            return (name.endsWith(SWF_EXTENSTION));

      }

}

 

%>