package com.agk.exporter;

import com.agk.model.GameResult;
import com.agk.model.Player;
import com.agk.model.TurnRecord;

import java.util.List;

public abstract class ReportGenerator {

    // --- Template Method ---
    public final void generateReport(GameResult result) {
        openDocument();
        writeHeader(result.getCaseId());
        writePlayers(result.getFinalPlayers());
        writeTurns(result.getTurns());
        writeFinalScores(result.getFinalPlayers());
        closeDocument();
    }

    // --- Steps subclasses must implement ---
    protected abstract void openDocument();
    protected abstract void writeLine(String text);
    protected abstract void closeDocument();

    // --- Shared helper methods (works for all formats) ---
    protected void writeHeader(String caseId) {
        writeLine("JEOPARDY GAME REPORT");
        writeLine("====================");
        writeLine("Case ID: " + caseId);
        writeLine("");
    }

    protected void writePlayers(List<Player> players) {
        writeLine("Players:");
        for (Player p : players) {
            writeLine("- " + p.getUsername());
        }
        writeLine("");
    }

    protected void writeTurns(List<TurnRecord> turns) {
        writeLine("Gameplay Summary:");
        writeLine("-----------------");

        int turn = 1;
        for (TurnRecord r : turns) {
            writeLine("Turn " + turn + ": " + r.playerName +
                    " selected " + r.category +
                    " for " + r.value + " pts");

            writeLine("Question: " + r.question);
            writeLine("Answer: " + r.answer +
                    " — " + (r.correct ? "Correct" : "Incorrect"));
            writeLine("Score after turn: " + r.playerName + " = " + r.runningTotal);
            writeLine("");

            turn++;
        }
    }

    protected void writeFinalScores(List<Player> players) {
        writeLine("Final Scores:");
        for (Player p : players) {
            writeLine(p.getUsername() + ": " + p.getScore());
        }
        writeLine("");
    }
}
