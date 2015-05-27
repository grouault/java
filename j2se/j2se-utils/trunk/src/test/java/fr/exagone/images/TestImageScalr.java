package fr.exagone.images;

import static java.lang.Boolean.TRUE;
import static org.imgscalr.Scalr.DEBUG_PROPERTY_NAME;
import static org.imgscalr.Scalr.resize;
import static org.junit.Assert.assertEquals;
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

public class TestImageScalr {

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
	public void testResizeSquarePicture() {

//		BufferedImage src = loadImage("event1-focus.jpg");
		BufferedImage src = loadImage("carousel-sport.gif");
		
		doTestSimpleResize(src, vignetteWidth, vignetteHeight);
		writePNG(dest, "user_picture_vignette.png");

		doTestSimpleResize(src, illustrationWidth, illustrationHeight);
		writePNG(dest, "user_picture_illustration.png");

		doTestSimpleResize(src, afficheWidth, afficheHeight);
		writePNG(dest, "user_picture_affiche.png");
	}

	@Test
	public void testResizeLandscapePicture() {

		BufferedImage src = loadImage("event_picture.jpg");

		doTestSimpleResize(src, vignetteWidth, vignetteHeight);
		writePNG(dest, "event_picture_vignette.png");

		doTestSimpleResize(src, illustrationWidth, illustrationHeight);
		writePNG(dest, "event_picture_illustration.png");

		doTestSimpleResize(src, afficheWidth, afficheHeight);
		writePNG(dest, "event_picture_affiche.png");
	}

	@Test
	public void testResizePortraitPicture() {

		BufferedImage src = loadImage("vinci_picture.jpeg");

		doTestSimpleResize(src, vignetteWidth, vignetteHeight);
		writePNG(dest, "vinci_picture_vignette.png");

		doTestSimpleResize(src, illustrationWidth, illustrationHeight);
		writePNG(dest, "vinci_picture_illustration.png");

		doTestSimpleResize(src, afficheWidth, afficheHeight);
		writePNG(dest, "vinci_picture_affiche.png");
	}

	private void doTestSimpleResize(BufferedImage src, int targetWidth,
			int targetHeight) {
		
		doTestResizeAuto(src, targetWidth, targetHeight);
//		doTestResizeToFrame(src, targetWidth, targetHeight);

		double srcRatio = src.getWidth() / src.getHeight();
		double destRatio = dest.getWidth() / dest.getHeight();

		assertEquals(srcRatio, destRatio, 0);
	}
	
	private void doTestResizeToFrame(BufferedImage src, int targetWidth,
			int targetHeight) {
		try {
			dest = resize(src, Method.QUALITY, Mode.FIT_TO_WIDTH, targetWidth);
			dest = resize(dest, Method.QUALITY, Mode.FIT_TO_HEIGHT, targetHeight);
		} catch (ImagingOpException e) {
			fail(e.getMessage());
		} finally {
			src.flush();
		}

		assertTrue(dest.getWidth() <= targetWidth);
		assertTrue(dest.getHeight() <= targetHeight);
	}
	
	private void doTestResizeAuto(BufferedImage src, int targetWidth,
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
