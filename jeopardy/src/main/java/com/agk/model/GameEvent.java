package com.agk.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameEvent {

    private String caseId;
    private String playerId;
    private String activity;
    private String timestamp;
    private String category;
    private int questionValue;
    private String answerGiven;
    private String result;
    private int scoreAfter;

    // ===== Correct Constructor (Matches Gameplay class) =====
    public GameEvent(
            String caseId,
            String playerId,
            String activity,
            LocalDateTime timestamp,
            String category,
            int questionValue,
            String answerGiven,
            String result,
            int scoreAfter
    ) {
        this.caseId = caseId;
        this.playerId = playerId;
        this.activity = activity;

        // Convert LocalDateTime → formatted String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = timestamp.format(formatter);

        this.category = category;
        this.questionValue = questionValue;
        this.answerGiven = answerGiven;
        this.result = result;
        this.scoreAfter = scoreAfter;
    }

    // ===== Getters =====
    public String getCaseId() {
        return caseId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getActivity() {
        return activity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCategory() {
        return category;
    }

    public int getQuestionValue() {
        return questionValue;
    }

    public String getAnswerGiven() {
        return answerGiven;
    }

    public String getResult() {
        return result;
    }

    public int getScoreAfter() {
        return scoreAfter;
    }


}
