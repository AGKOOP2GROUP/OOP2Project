package com.agk.model;

public class Player {
    private final int id;
    private final String username;
    private int score = 0;

    //constructor
    public Player(int id, String username){
        this.id = id;
        this.username = username;
    }

    //getters
    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public int getScore(){
        return score;
    }

    //when players answer questions their individual scores are adjusted
    public void updateScore(int marks){
        this.score += marks;
    }
}
