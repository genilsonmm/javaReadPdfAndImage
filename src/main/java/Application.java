//import com.aspose.pdf.*;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        //readFromPDf();
        readFromImage();
    }

    public static void readFromPDf(){
        String path = "C:\\Temp\\teste.pdf";

        try {
            PDDocument doc = PDDocument.load(new File(path));
            String text = new PDFTextStripper().getText(doc);;
            System.out.println("Text in PDF\n---------------------------------");
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromImage(){
        File imageFile = new File("./src/main/resources/img.png");
        Tesseract tess4j = new Tesseract();
        tess4j.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        tess4j.setLanguage("por");
        try {
            String result = tess4j.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}