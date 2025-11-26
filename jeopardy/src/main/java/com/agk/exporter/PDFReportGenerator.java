// package com.agk.exporter;
// import org.apache.pdfbox.pdmodel.*;
// import org.apache.pdfbox.pdmodel.font.*;

// public class PDFReportGenerator extends ReportGenerator {

//     private PDDocument doc;
//     private PDPageContentStream stream;

//     @Override
//     protected void openDocument() {
//         try {
//             doc = new PDDocument();
//             PDPage page = new PDPage();
//             doc.addPage(page);
//             stream = new PDPageContentStream(doc, page);
//             stream.setLeading(14.5f);
//             stream.beginText();
//             stream.setFont(PDType1Font.HELVETICA, 12);
//             stream.newLineAtOffset(25, 700);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     protected void writeLine(String text) {
//         try {
//             stream.showText(text);
//             stream.newLine();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     protected void closeDocument() {
//         try {
//             stream.endText();
//             stream.close();
//             doc.save("report.pdf");
//             doc.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
