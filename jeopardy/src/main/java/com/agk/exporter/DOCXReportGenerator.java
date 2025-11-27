package com.agk.exporter;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileOutputStream;
import java.io.IOException;

public class DOCXReportGenerator extends ReportGenerator {

    private XWPFDocument document;

    @Override
    protected void openDocument() {
        document = new XWPFDocument();
    }

    @Override
    protected void writeLine(String text) {
        XWPFParagraph para = document.createParagraph();
        para.createRun().setText(text);
    }

    @Override
    protected void closeDocument() {
        try (FileOutputStream out = new FileOutputStream("JeopardyReport.docx")) {
            document.write(out);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save DOCX", e);
        }
    }
}
