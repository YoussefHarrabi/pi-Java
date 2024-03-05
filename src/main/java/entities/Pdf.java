package entities;
import javafx.scene.control.TableView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class Pdf {
    public static void generatePDF(TableView<Commun_means_of_transport> list, String filePath) throws IOException, IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                for (Commun_means_of_transport listMoy : list.getItems() ) {
                    contentStream.showText("Registration Number: " + listMoy.getRegistration_number());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Type: " + listMoy.getType());

                    // Ajoutez d'autres champs si nécessaire
                    contentStream.newLineAtOffset(0, -20);
                }

                contentStream.endText();
            }

            document.save(filePath);
        }
    }
}