package alphaparser.loader.loader;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfLoader {
    private List<File> files;

    public PdfLoader(String directoryPath) {
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles((dir, name) -> name.endsWith(".pdf"));

        files = List.of(fileList);

        Logger.info("{} files found", files.size());
    }

    public List<String> loadDocs() throws IOException {
        Logger.info("Started documento loading");
        List<String> docs = new ArrayList<>();
        for (var file : files) {
            PDDocument pdDoc = null;
            COSDocument cosDoc = null;
            PDFTextStripper pdfStripper;
            String parsedText;
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            docs.add(parsedText);
            pdDoc.close();
        }
        Logger.info("Finished Document Loading");
        return docs;
    }
}
