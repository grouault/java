package fr.exagone.jse.itext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfBonLivraison {

	/**
	 * Merge des documents
	 * - les CGVs sont intégrés uniquement à la fin du document.
	 * 
	 * @param bonLivraison
	 * @param rectoFile
	 * @param versoFile
	 * @param pathDestination
	 */
    public static void mergeAvecFondEtCgvALaFin(File bonLivraison, File rectoFile, File versoFile, String pathDestination) {
        try {
            // lecture et définition du pdf de sortie
            String originalPdfPath = bonLivraison.getAbsolutePath();
            PdfReader reader = new PdfReader(originalPdfPath);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pathDestination));

            // construction de l'objet image fond (traiter ko)
            String absolutePathFond = rectoFile.getAbsolutePath();
            Image imgFond = Image.getInstance(absolutePathFond);
            Rectangle pagesize = reader.getPageSize(1);
            imgFond.scaleToFit(pagesize.getWidth(), pagesize.getHeight());
            imgFond.setAbsolutePosition(0, 0);

            // merge FondBL
            for (int i = 1; i < reader.getNumberOfPages() + 1; i++) {
                stamper.getUnderContent(i).addImage(imgFond);
            }

            // construction de l'objet page cgv (traiter ko)
            String absolutePathCgv = versoFile.getAbsolutePath();
            PdfReader cgv = new PdfReader(absolutePathCgv);
            PdfImportedPage page = stamper.getImportedPage(cgv, 1); // prendre la 1er page du doc de référence

            // merge CGV en fin de doc
            //
            // ajout d'une page blanche à la fin du BL
            int nbPagesBl = reader.getNumberOfPages();
            stamper.insertPage(nbPagesBl + 1, reader.getPageSize(1)); 
            // recuperation de la page de fin.
            PdfContentByte pageFin = stamper.getOverContent(nbPagesBl + 1);
            // on met la page 'CGV' en superposition de la page blanche
            pageFin.addTemplate(page, 0, 0);
            
            // close
            cgv.close();
            stamper.close();
            reader.close();

        } catch (IOException | DocumentException e) {
        	System.out.println("Erreur lors de la fusion d'un PDF avec ses fonds");
            // logger.log(Level.SEVERE, "Erreur lors de la fusion d'un PDF avec ses fonds", e);
        }
        
    }
	
    /**
     * Merge des documents 
     * - les cgvs sont intégrés entre chaque page du BL.
     * 
     * @param bonLivraison
     * @param rectoFile
     * @param versoFile
     * @param pathDestination
     */
    public static void mergeAvecFondEtCgv(File bonLivraison, File rectoFile, File versoFile, String pathDestination) {
        try {
            // lecture et définition du pdf de sortie
            String originalPdfPath = bonLivraison.getAbsolutePath();
            PdfReader reader = new PdfReader(originalPdfPath);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pathDestination));

            // construction de l'objet image fond (traiter ko)
            String absolutePathFond = rectoFile.getAbsolutePath();
            Image imgFond = Image.getInstance(absolutePathFond);
            Rectangle pagesize = reader.getPageSize(1);
            imgFond.scaleToFit(pagesize.getWidth(), pagesize.getHeight());
            imgFond.setAbsolutePosition(0, 0);

            // merge
            for (int i = 1; i < reader.getNumberOfPages() + 1; i++) {
                stamper.getUnderContent(i).addImage(imgFond);
            }

            // construction de l'objet page cgv (traiter ko)
            String absolutePathCgv = versoFile.getAbsolutePath();
            PdfReader cgv = new PdfReader(absolutePathCgv);
            PdfImportedPage page = stamper.getImportedPage(cgv, 1); // prendre la 1er page du doc de référence

            // merge
            int nbPages = reader.getNumberOfPages() * 2;
            for (int i = 1; i < nbPages; i = i + 2) {
                stamper.insertPage(i + 1, reader.getPageSize(1)); // insertion d'une page blanche en 2
                PdfContentByte page1 = stamper.getOverContent(i + 1);
                page1.addTemplate(page, 0, 0); // on met la page en superposition de la page blanche
            }

            // close
            cgv.close();
            stamper.close();
            reader.close();

        } catch (IOException | DocumentException e) {
        	System.out.println("Erreur lors de la fusion d'un PDF avec ses fonds");
            // logger.log(Level.SEVERE, "Erreur lors de la fusion d'un PDF avec ses fonds", e);
        }
        
    }
    
    
    public static void mergeAvecSignature(File pdfSourceFile, File signatureFile, String pathMergeFile)
            throws IOException, DocumentException {
    	
        // Definir un reader pour le pdf d'origine
        PdfReader reader = new PdfReader(pdfSourceFile.getAbsolutePath());

        // Definir un writer servant a ecrire le resultat dans un fichier temporaire du meme repertoire
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pathMergeFile));

        // Ajouter l'image representant la signature dans le pdf temporaire
        Image imgSignature = Image.getInstance(signatureFile.getAbsolutePath());
        imgSignature.setAbsolutePosition(450f, 180f);
        // imgSignature.setAbsolutePosition(250f, 300f);
        imgSignature.scaleToFit(75f, 75f);
        stamper.getOverContent(reader.getNumberOfPages()).addImage(imgSignature);

        // Fermer les streams proprement
        stamper.close();
        reader.close();

    }
	
}
