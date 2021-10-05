import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * Class responsible for reading PDF Files
 */
public class VIACReader {

    /**
     * Reads a textfile and returns content as String
     * @return PDF Content
     */
    public static String readFile(File file){
        PDDocument document = null;
        String text = null;

        try{
            //Loading an existing document
            document = PDDocument.load(file);

            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            //Retrieving text from PDF document
            text = pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }
}
