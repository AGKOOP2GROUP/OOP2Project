package com.agk.exporter;

import java.io.PrintWriter;

public class TXTReportGenerator extends ReportGenerator {

    private PrintWriter writer;

    @Override
    protected void openDocument() {
        try {
            writer = new PrintWriter("report.txt");
        } catch (Exception e) {
            throw new RuntimeException("Unable to create TXT file", e);
        }
    }

    @Override
    protected void writeLine(String text) {
        writer.println(text);
    }

    @Override
    protected void closeDocument() {
        writer.close();
    }
}
