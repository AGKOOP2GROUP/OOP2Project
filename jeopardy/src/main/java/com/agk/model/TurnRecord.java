package com.agk.model;

public class TurnRecord {

    public String playerName;
    public String category;
    public int value;
    public String question;
    public String answer;
    public boolean correct;
    public int pointsEarned;
    public int runningTotal;
    public int scoreAfterTurn;
    public int turnNumber; 

      public TurnRecord(String playerName, String category, int value,
                      String question, String answer, boolean correct, int pointsEarned,
                      int scoreAfterTurn) {
        this.playerName = playerName;
        this.category = category;
        this.value = value;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
        this.pointsEarned = pointsEarned;
        this.scoreAfterTurn = scoreAfterTurn;
    }

}
