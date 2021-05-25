package fr.exagone.itext;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.itextpdf.text.DocumentException;

import fr.exagone.jse.itext.PdfBonLivraison;
import junit.framework.Assert;

public class TestFusion {

	@Test
	public void generer() {

		// Emplacement relatif par raport a la racine du projet.
		String relatifRessouceFolder = "\\src\\test\\resources\\itext\\fusion\\";
		// fichier BL
		String relativePathFileBL = relatifRessouceFolder + "bl\\final\\BLENRI-2-pages.pdf";
		// fichier CGV
		String relativePathFileCGV = relatifRessouceFolder + "cgv\\cgv.pdf";
		// fichier FondBL
		String relativePathFondBL= relatifRessouceFolder + "fondbl\\fond_ppf.jpg";
		String relativePathFondBLDup= relatifRessouceFolder + "fondbl\\Fond5DUP.jpg";
		// fichie de resultat
		String relativePathResult = relatifRessouceFolder + "result\\bl-fond-cgv\\";
		
		// baseDir: pointe à la racine du projet.
		File baseDir = new File(System.getProperty("basedir", "")).getAbsoluteFile();
		System.out.println("baseDir = " + baseDir);
		
		// fichier nécessaire pour le process
		File fileBL = new File(baseDir, relativePathFileBL);
		File fileCGV = new File(baseDir, relativePathFileCGV);
		File fileFondBL = new File(baseDir, relativePathFondBL);
		File fileFondBLDup = new File(baseDir, relativePathFondBLDup);
		
		Assert.assertTrue(fileBL.exists());
		Assert.assertTrue(fileCGV.exists());
		Assert.assertTrue(fileFondBL.exists());
		
		// url fichier de resultat
		// String urlFichierResult = baseDir.getPath() + relativePathResult + fileBL.getName();
		String urlFichierAvecFontEtCgv = baseDir.getPath() + relativePathResult + "test-gildas.pdf";
		String urlFichierAvecFond = baseDir.getPath() + relativePathResult + "test-avec-fond.pdf";
		
		System.out.println("urlFichierAvecFontEtCgv = " + urlFichierAvecFontEtCgv);
		
		// Fusion. BL / Fond / Cgv
		PdfBonLivraison.mergeAvecFondEtCgvALaFin(fileBL, fileFondBL, fileCGV, urlFichierAvecFontEtCgv);
		File fileAvecFondEtCgv = new File(urlFichierAvecFontEtCgv);
		Assert.assertTrue(fileAvecFondEtCgv.exists());
		
		// Fusion. BL / Fond
		PdfBonLivraison.mergeAvecFond(fileBL, fileFondBLDup, urlFichierAvecFond);
		File fileAvecFond = new File(urlFichierAvecFond);
		Assert.assertTrue(fileAvecFond.exists());
		
	}
	

	
	@Test
	public void fusionSignature() {

		// Emplacement relatif par raport a la racine du projet.
		String relatifRessouceFolder = "\\src\\test\\resources\\itext\\fusion\\";
		// fichier BL
		String relativePathFileBL = relatifRessouceFolder + "bl\\integration\\BL-1-page-1colis.pdf";
		// String relativePathFileBL = relatifRessouceFolder + "bl\\integration\\BL-2-pages.pdf";
		// fichier signature
		String relativePathFileSignature = relatifRessouceFolder + "signatures\\signature-1.png";
		// fichie de resultat
		String relativePathResult = relatifRessouceFolder + "result\\signatures\\";
		
		// baseDir: pointe à la racine du projet.
		File baseDir = new File(System.getProperty("basedir", "")).getAbsoluteFile();
		System.out.println("baseDir = " + baseDir);
		
		// fichier nécessaire pour le process
		File fileBL = new File(baseDir, relativePathFileBL);
		File fileSignature = new File(baseDir, relativePathFileSignature);
		
		Assert.assertTrue(fileBL.exists());
		Assert.assertTrue(fileSignature.exists());
		
		// url fichier de resultat
		// String urlFichierResult = baseDir.getPath() + relativePathResult + fileBL.getName();
		String urlFichierResult = baseDir.getPath() + relativePathResult + "bl_merge.pdf";
		System.out.println("urlFichierResult = " + urlFichierResult);
		
		// Fusion.
		try {
			PdfBonLivraison.mergeAvecSignature(fileBL, fileSignature, urlFichierResult);
		} catch (IOException | DocumentException e) {
			System.out.println("Erreur lors de la fusion : " + e.getMessage());
			e.printStackTrace();
		}
		File fileMerged = new File(urlFichierResult);
		
		Assert.assertTrue(fileMerged.exists());
		
	}
	
	
	
}
