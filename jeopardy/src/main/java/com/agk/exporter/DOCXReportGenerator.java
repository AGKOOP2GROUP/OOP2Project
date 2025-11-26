// package com.agk.exporter;

// import com.agk.model.GameResult;
// import org.apache.poi.xwpf.usermodel.XWPFDocument;
// import org.apache.poi.xwpf.usermodel.XWPFParagraph;

// import java.io.FileOutputStream;

// public class DOCXReportGenerator extends ReportGenerator {

//     private XWPFDocument document;
//     private FileOutputStream out;

//     @Override
//     protected void openDocument() {
//         try {
//             document = new XWPFDocument();
//             out = new FileOutputStream("report.docx");
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to open DOCX file.", e);
//         }
//     }

//     @Override
//     protected void writeLine(String text) {
//         XWPFParagraph p = document.createParagraph();
//         p.createRun().setText(text);
//     }

//     @Override
//     protected void closeDocument() {
//         try {
//             document.write(out);
//             out.close();
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to save DOCX file.", e);
//         }
//     }
// }
