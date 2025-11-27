package com.agk.exporter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFReportGenerator extends ReportGenerator {

    private PDDocument document;
    private PDPageContentStream contentStream;
    private float yPosition;
    private final String filename;

    public PDFReportGenerator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void openDocument() {
        try {
            document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            // Start a text block ONCE per page
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);

            yPosition = 750;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeLine(String text) {
        try {
            // Make new page if needed
            if (yPosition < 50) {
                contentStream.endText();
                contentStream.close();

                PDPage newPage = new PDPage();
                document.addPage(newPage);

                contentStream = new PDPageContentStream(document, newPage);
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);

                yPosition = 750;
            }

            contentStream.showText(text);
            contentStream.newLineAtOffset(0, -20);

            yPosition -= 20;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void closeDocument() {
        try {
            contentStream.endText();
            contentStream.close();

            document.save(filename);
            document.close();

            System.out.println("PDF saved to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
