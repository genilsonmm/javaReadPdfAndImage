//import com.aspose.pdf.*;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.File;
import java.io.IOException;

import static com.amazonaws.regions.Regions.US_WEST_2;

public class Application {

    public static void main(String[] args) {
        //readFromPDf();
        //readFromImage();
        translate("");
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

    public static void translate(String text) {
        try {
            // Create credentials using a provider chain. For more information, see
            // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
            AWSCredentialsProvider awsCreds = new ProfileCredentialsProvider("TestSDK");

            AmazonTranslate translate = AmazonTranslateClient.builder()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds.getCredentials()))
                    .withRegion(US_WEST_2)
                    .build();

            TranslateTextRequest request = new TranslateTextRequest()
                    .withText("Hello, world")
                    .withSourceLanguageCode("en")
                    .withTargetLanguageCode("es");
            TranslateTextResult result = translate.translateText(request);
            System.out.println(result.getTranslatedText());
        }
        catch (Exception error){
            System.out.println(error.getMessage());
        }

    }
}