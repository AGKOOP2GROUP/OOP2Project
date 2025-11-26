package com.agk.model;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private static int counter = 1;   // increments per game

    private String caseId;
    private final List<TurnRecord> turns = new ArrayList<>();
    private List<Player> finalPlayers = new ArrayList<>();

    public GameResult() {
        this.caseId = String.format("GAME%03d", counter++);
    }

    public String getCaseId() {
        return caseId;
    }

    public void addTurn(TurnRecord rec) {
        turns.add(rec);
    }

    public List<TurnRecord> getTurns() {
        return turns;
    }

    public void setFinalPlayers(List<Player> players) {
        this.finalPlayers = players;
    }

    public List<Player> getFinalPlayers() {
        return finalPlayers;
    }
}
