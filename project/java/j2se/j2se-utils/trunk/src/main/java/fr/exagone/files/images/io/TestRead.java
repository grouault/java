package fr.exagone.files.images.io;

import java.awt.Image;
import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;

import org.apache.xmlgraphics.java2d.color.DeviceCMYKColorSpace;

public class TestRead {

	public static final String URL_IMAGE_ERROR = "http://static.voila.fr/Images/blocs/Magic/140544.jpg";
	private static String FILE_TEST = "C:/Temp/devs/bandoweb_forum.jpg";
	private static String FILE_DEST = "C:/Temp/devs/file_dest.jpg";
	
	private static final String URL_IMAGE = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Pinocchio_1940.jpg/290px-Pinocchio_1940.jpg";
	
	//	private static String FILE_TEST = "C:/Temp/devs/denise.jpg";
	
	
	public static void main(String[] args) {
		Image image;
		try {
			
			BufferedImage buffer = read(URL_IMAGE);
			System.out.println("Buffer = " + buffer);
			
////			image = readCMYKImage(new File(FILE_TEST));
////			image = convertImage(new File(FILE_TEST));
//			if (image != null) {
//				System.out.println("image ok");
//			} else {
//				System.out.println("image ko");
//			}
			
		} catch (Exception e) {
			System.out.println("ex : " + e.getMessage());
		}

	}
	
	
	/**
	 * ImageIO cannot read CMYK-jpegs, it throws IIOException(Unsupported Image Type).
	 * This method tries to read cmyk image.
	 * @param file
	 * @return  image TYPE_4BYTE_ABGR
	 * @throws Exception
	 */
	public static BufferedImage readCMYKImage(File file) throws Exception {
	    Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
	    ImageReader reader = null;
	    while(readers.hasNext()) {
	        reader = readers.next();
	        if(reader.canReadRaster())
	            break;
	    }
	    FileInputStream fis = new FileInputStream(file);
	    FileOutputStream fios = new FileOutputStream(FILE_DEST);
	    try {
	        ImageInputStream input = ImageIO.createImageInputStream(fis); 
	        reader.setInput(input); // original CMYK-jpeg stream
	        Raster raster = reader.readRaster(0, null); // read image raster 
	        BufferedImage image = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_RGB);
	        image.getRaster().setRect(raster);
	        convertImage(image);
	        ImageIO.write(image, "jpeg", fios);
	        return image;
	    } finally {
	        try { fis.close(); } catch(Exception ex) {}
	    }
	}
	
	public static Image convertImage(BufferedImage img) {
		
		/*
		BufferedImage img = null;
		try {
		    img = ImageIO.read(file);
		} catch (IOException e) {
		
		}
		*/
		
		ColorSpace cmyk = DeviceCMYKColorSpace.getInstance();
		int w = img.getWidth(), h = img.getHeight();
		BufferedImage image = null;
		
		byte[] buffer = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		/*
		byte[] buffer = new DataBufferByte(
				img.getRaster().getDataBuffer(), ).getData(); 
		*/
		
		int pixelCount = buffer.length;
		byte[] new_data = new byte[pixelCount / 4 * 3];
		float lastC = -1, lastM = -1, lastY = -1, lastK = -1;
		float C, M, Y, K;
		float[] rgb = new float[3];
		// loop through each pixel changing CMYK values to RGB
		int pixelReached = 0;

		for (int i = 0 ; i < pixelCount ; i += 4) {
		    C = (buffer[i] & 0xff) / 255f;
		    M = (buffer[i + 1] & 0xff) / 255f;
		    Y = (buffer[i + 2] & 0xff) / 255f;
		    K = (buffer[i + 3] & 0xff) / 255f;
		    if (lastC == C && lastM == M && lastY == Y && lastK == K) {
		        //use existing values if not changed
		    } else { //work out new
		        rgb = cmyk.toRGB(new float[] {C, M, Y, K});
		        //cache values
		        lastC = C;
		        lastM = M;
		        lastY = Y;
		        lastK = K;
		    }
		    new_data[pixelReached++] = (byte) (rgb[0] * 255);
		    new_data[pixelReached++] = (byte) (rgb[1] * 255);
		    new_data[pixelReached++] = (byte) (rgb[2] * 255);
		}

		// turn data into RGB image
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		int[] l_bandoff = {0, 1, 2};
		PixelInterleavedSampleModel l_sm = new PixelInterleavedSampleModel(DataBuffer.TYPE_INT, w, h, 3, w * 3, l_bandoff);
		// image.setData(new ByteInterleavedRaster(l_sm, new DataBufferByte(new_data, new_data.length), new Point(0, 0)));

		// write
		try {
			ImageIO.write(image, "jpg", new File("C:/Temp/devs/rgb.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return image;
		
	}
	
	public static BufferedImage read(final String urlStr){
		BufferedImage buffer = null;
		try {
			URI uri = new URI(urlStr);
			URL url = uri.toURL();
			buffer = ImageIO.read( url );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer;
	}

		
	public static Image getImageByInputStream(File file) {
		
		Image img = null;
		ImageInputStream iis = null;
		try {
			iis = new FileImageInputStream(file);
			img = ImageIO.read(iis);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		    
		}
		return img;
		
	}	
	
	public static Image getImage(File file) {
	
		Image img = null;
		ImageInputStream iis = null;
		try {
		
			iis = new FileImageInputStream(file);
		    for (Iterator<ImageReader> i = ImageIO.getImageReaders(iis); 
		         img == null && i.hasNext(); ) {
		        ImageReader r = i.next();
		        try {
		            r.setInput(iis);
		            img = r.read(0);
		        } catch (IOException e) {
		        	System.out.println("error : " + e.getMessage());
		        }
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		    
		}
		
		return img;
	
	}
	
	
}
