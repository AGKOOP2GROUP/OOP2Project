// package com.agk;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// import com.agk.exporter.ReportGenerator;
// import com.agk.exporter.TXTReportGenerator;
// import com.agk.exporter.PDFReportGenerator;
// import com.agk.exporter.DOCXReportGenerator;

// import java.io.File;

// /**
//  * JUnit tests for the ReportGenerator classes.
//  */
// public class ReportGeneratorTests {

//     private ReportGenerator txtReportGenerator;
//     private ReportGenerator pdfReportGenerator;
//     private ReportGenerator docxReportGenerator;

//     @BeforeEach
//     void setup() {
//         txtReportGenerator = new TXTReportGenerator();
//         pdfReportGenerator = new PDFReportGenerator();
//         docxReportGenerator = new DOCXReportGenerator();
//     }

//     @Test
//     void testTXTReportGeneration() {
//         boolean result = txtReportGenerator.generateReport("Sample TXT Report");
//         assertTrue(result, "TXT report should be generated successfully");
//     }

//     @Test
//     void testPDFReportGeneration() {
//         boolean result = pdfReportGenerator.generateReport("Sample PDF Report");
//         assertTrue(result, "PDF report should be generated successfully");
//     }

//     @Test
//     void testDOCXReportGeneration() {
//         boolean result = docxReportGenerator.generateReport("Sample DOCX Report");
//         assertTrue(result, "DOCX report should be generated successfully");
//     }

//     @Test
//     void testGeneratedFilesExist() {
//         txtReportGenerator.generateReport("TXT Report Test");
//         pdfReportGenerator.generateReport("PDF Report Test");
//         docxReportGenerator.generateReport("DOCX Report Test");

//         File txtFile = new File("TXT Report Test.txt");
//         File pdfFile = new File("PDF Report Test.pdf");
//         File docxFile = new File("DOCX Report Test.docx");

//         assertTrue(txtFile.exists(), "TXT file should exist after generation");
//         assertTrue(pdfFile.exists(), "PDF file should exist after generation");
//         assertTrue(docxFile.exists(), "DOCX file should exist after generation");

//         // Clean up generated files
//         txtFile.delete();
//         pdfFile.delete();
//         docxFile.delete();
//     }

//     @Test
//     void testReportContentNotEmpty() {
//         txtReportGenerator.generateReport("TXT Content Test");
//         File txtFile = new File("TXT Content Test.txt");
//         assertTrue(txtFile.length() > 0, "Generated TXT report should not be empty");
//         txtFile.delete();
//     }
// }
