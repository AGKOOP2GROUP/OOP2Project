// package com.agk.exporter;

// import com.agk.model.GameResult;
// import com.itextpdf.text.Document;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.pdf.PdfWriter;

// import java.io.FileOutputStream;

// public class PDFReportGenerator extends ReportGenerator {

//     private Document document;

//     @Override
//     protected void openDocument() {
//         try {
//             document = new Document();
//             PdfWriter.getInstance(document, new FileOutputStream("report.pdf"));
//             document.open();
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to open PDF file.", e);
//         }
//     }

//     @Override
//     protected void writeLine(String text) {
//         try {
//             document.add(new Paragraph(text));
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to write PDF line.", e);
//         }
//     }

//     @Override
//     protected void closeDocument() {
//         document.close();
//     }
// }
