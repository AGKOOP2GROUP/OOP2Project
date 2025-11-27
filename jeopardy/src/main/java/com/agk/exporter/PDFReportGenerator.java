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
            yPosition = 750; // start near top
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeLine(String text) {
        try {
            if (yPosition < 50) { // create new page if too low
                contentStream.endText();
                contentStream.close();
                PDPage newPage = new PDPage();
                document.addPage(newPage);
                contentStream = new PDPageContentStream(document, newPage);
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                yPosition = 750;
            }

            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText(text);
            contentStream.endText();
            yPosition -= 20; // move down for next line
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void closeDocument() {
        try {
            contentStream.close();
            document.save(filename);
            document.close();
            System.out.println("PDF saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
