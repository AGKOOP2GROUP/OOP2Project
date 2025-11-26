package com.agk.exporter;

import java.io.PrintWriter;
import java.util.List;

import com.agk.model.GameResult;
import com.agk.model.Player;
import com.agk.model.TurnRecord;

public class TXTReportGenerator extends ReportGenerator {

    private PrintWriter writer;

    @Override
    protected void openDocument() {
        try {
            writer = new PrintWriter("report.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void writeLine(String text) {
        writer.println(text);
    }

    @Override
    protected void writeHeader(String caseId) {
        writeLine("JEOPARDY GAME REPORT");
        writeLine("Case ID: " + caseId);
        writeLine("----------------------------------------");
    }

    @Override
    protected void writePlayers(List<Player> players) {
        writeLine("Players:");
        for (Player p : players) {
            writeLine(" - " + p.getUsername());
        }
        writeLine("");
    }

    @Override
    protected void writeTurns(List<TurnRecord> turns) {
        writeLine("Gameplay Summary:");
        writeLine("-----------------");
        for (TurnRecord t : turns) {
            writeLine("Player: " + t.playerName);
            writeLine("Category: " + t.category);
            writeLine("Question Value: " + t.value);
            writeLine("Question: " + t.question);
            writeLine("Answer Given: " + t.answer);
            writeLine("Correct: " + t.correct);
            writeLine("Points Earned: " + t.pointsEarned);
            writeLine("Running Total: " + t.scoreAfterTurn);
            writeLine("----------------------------------------");
        }
        writeLine("");
    }

    @Override
    protected void writeFinalScores(List<Player> players) {
        writeLine("Final Scores:");
        for (Player p : players) {
            writeLine(p.getUsername() + ": " + p.getScore());
        }
    }

    @Override
    protected void closeDocument() {
        if (writer != null) {
            writer.close();
        }
    }
}
