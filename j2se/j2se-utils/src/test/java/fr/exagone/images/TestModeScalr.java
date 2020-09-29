package fr.exagone.images;

import static java.lang.Boolean.TRUE;
import static org.imgscalr.Scalr.DEBUG_PROPERTY_NAME;
import static org.imgscalr.Scalr.resize;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestModeScalr {

	private static final int vignetteWidth = 150;
	private static final int vignetteHeight = 150;
	private static final int illustrationWidth = 650;
	private static final int illustrationHeight = 300;
	private static final int afficheWidth = 150;
	private static final int afficheHeight = 250;
	
	private BufferedImage dest;

	@BeforeClass
	public static void turnDebugOn() {
		System.setProperty(DEBUG_PROPERTY_NAME, TRUE.toString());
	}

	@After
	public void flushDestImage() {
		if (dest != null) {
			dest.flush();
		}
	}
	
	@Test
	public void testResizeLandscapePicture() {
//		BufferedImage src = loadImage("automne.jpeg");
//		BufferedImage src = loadImage("desert.jpeg");
//		BufferedImage src = loadImage("barrique-530-800.jpeg");
//		BufferedImage src = loadImage("test-img-88-46.png");
//		BufferedImage src = loadImage("carousel-sport.gif");
		BufferedImage src = loadImage("affiche-140-281.jpeg");
		// traitement illustration
		buidPaysage(src, illustrationWidth, illustrationHeight);
		writePNG(dest, "testimg-illustration.png");
		// traitement affiche
		buidPortrait(src, afficheWidth, afficheHeight);
		writePNG(dest, "testimg-portrait.png");	
		// traitement affiche
		buidVignette(src, vignetteWidth, vignetteHeight);
		writePNG(dest, "testimg-vignette.png");	
	
	}
	
	
	private void buidPaysage(BufferedImage src, int targetWidth,
			int targetHeight) {
		try {
			// resizing sur la width.
			dest = resize(src, Method.QUALITY, Mode.FIT_TO_WIDTH, targetWidth);
			if (dest.getHeight() > targetHeight) {
				// resizing sur la height.
				dest = resize(dest, Method.QUALITY, Mode.FIT_TO_HEIGHT, targetHeight);
			}
			
		} catch (ImagingOpException e) {
			fail(e.getMessage());
		} finally {
			src.flush();
		}
		assertTrue(dest.getWidth() <= targetWidth);
		assertTrue(dest.getHeight() <= targetHeight);
	}
	
	
	private void buidPortrait(BufferedImage src, int targetWidth,
			int targetHeight) {
		try {
			// resizing sur la width.
			dest = resize(src, Method.QUALITY, Mode.FIT_TO_HEIGHT, targetHeight);
			if (dest.getWidth() > targetWidth) {
				// resizing sur la height.
				dest = resize(dest, Method.QUALITY, Mode.FIT_TO_WIDTH, targetWidth);
			}
		} catch (ImagingOpException e) {
			fail(e.getMessage());
		} finally {
			src.flush();
		}
		assertTrue(dest.getWidth() <= targetWidth);
		assertTrue(dest.getHeight() <= targetHeight);
	}
	
	private void buidVignette(BufferedImage src, int targetWidth,
			int targetHeight) {
		try {
			dest = resize(src, Method.QUALITY, Mode.AUTOMATIC, targetWidth,
					targetHeight);
		} catch (ImagingOpException e) {
			fail(e.getMessage());
		} finally {
			src.flush();
		}

		assertTrue(dest.getWidth() <= targetWidth);
		assertTrue(dest.getHeight() <= targetHeight);
	}
	
	private BufferedImage loadImage(String inputName) {
		URL imageURL = getClass().getResource("/imagescalr/" + inputName);
		BufferedImage image = null;

		try {
			image = ImageIO.read(imageURL);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		return image;
	}

	private void writePNG(BufferedImage image, String outputName) {
		final String pngFormatName = "png";

		File outputFile = Paths
				.get(System.getProperty("user.home"), outputName).toFile();

		try {
			ImageIO.write(image, pngFormatName, outputFile);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}  
	
}
