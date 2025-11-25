package com.agk.model;

public class Player {
    private final int id;
    private final String username;
    private int score = 0;

    public Player(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public int getScore(){
        return score;
    }

    public void updateScore(int marks){
        this.score += marks;
    }
}
