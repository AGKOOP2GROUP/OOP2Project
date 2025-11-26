// package com.agk.exporter;
// import java.io.FileOutputStream;

// import org.apache.poi.xwpf.usermodel.*;

// public class DOCXReportGenerator extends ReportGenerator {

//     private XWPFDocument doc;
//     private FileOutputStream out;

//     @Override
//     protected void openDocument() {
//         try {
//             doc = new XWPFDocument();
//             out = new FileOutputStream("report.docx");
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     protected void writeLine(String text) {
//         XWPFParagraph p = doc.createParagraph();
//         XWPFRun run = p.createRun();
//         run.setText(text);
//     }

//     @Override
//     protected void closeDocument() {
//         try {
//             doc.write(out);
//             out.close();
//             doc.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
