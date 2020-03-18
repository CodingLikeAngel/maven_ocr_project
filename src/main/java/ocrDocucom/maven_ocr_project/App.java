package ocrDocucom.maven_ocr_project;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;;

/* Java Ocr recognition Tesseract Integration 
 * 
 * 
 * 
 * Started by Angel Nieto 18-03-2020 */  

public class App 
{
    public static void main( String[] args ) throws TesseractException, IOException
    {
    	
    	Color_And_Write_Image();
        ITesseract image = new Tesseract();  	
    	String str = image.doOCR(new File("C:\\Users\\aniet\\Desktop\\prueba\\ipsumProcessed2.png"));  
        System.out.println( "OCR RESULT:");
        System.out.println( " ");
        System.out.println(str);
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        java.awt.Image tmp = img.getScaledInstance(newW, newH, BufferedImage.SCALE_DEFAULT);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
    
    
    public static void Color_And_Write_Image() throws IOException
    {
    	BufferedImage img = null;
        img = ImageIO.read(new File("C:\\eclipse-workspace\\maven_ocr_project\\Files\\Test\\ipsum.jpg"));
        img = resize(img , img.getWidth() *4 , img.getHeight() *2 );

            for (int x = 0; x < img.getWidth() ; x++)  {  //- img.getWidth()/2
            for (int y = 0; y < img.getHeight() ; y++) {  //- img.getHeight()/2
                Color color = new Color(img.getRGB(x, y));
                int red = color.getRed();
                int green = color.getBlue();
                int blue = color.getGreen();
                if (blue + red + green > 255) {
                    green = 255; // Black
                    red = 255;
                    blue = 255;
                }
                else if  (blue + red + green < 255) {
                    green = 155; // Black
                    red = 0;
                    blue = 0;
                }

                Color col = new Color(red, green, blue);
                img.setRGB(x, y, col.getRGB());
            }
        }

      ImageIO.write(img , "png", new File("C:\\eclipse-workspace\\maven_ocr_project\\Files\\ProcessingResult\\ipsumProcessed2.png"));
  	
    }
    
}
