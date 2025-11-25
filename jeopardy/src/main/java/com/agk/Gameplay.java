package com.agk;

import java.util.ArrayList;
import java.util.List;

import com.agk.model.JeopardyQuestions;
import com.agk.model.Player;

public class Gameplay {
    private String gameId;
    private List<Player> players = new ArrayList<>();
    private final JeopardyQuestions jeopardyQuestions;
    private int currPlayer = 0;

    public Gameplay(JeopardyQuestions jeopardyQuestions) {
        this.jeopardyQuestions = jeopardyQuestions;
    }

    public JeopardyQuestions getJeopardyQuestions() {
        return jeopardyQuestions;
    }

    public List<String> getCategories() {
        if (jeopardyQuestions == null) 
            return null;
        return jeopardyQuestions.getCategories();
    }

    public List<String> getPlayers(){
        List<String> pnames = new ArrayList<>();
        for (Player p: players){
            pnames.add(p.getUsername());
        }
        return pnames;
    }

    public void addPlayer(String name){
        if (name == null)
            return;
        Player p = new Player(players.size() + 1, name);
        players.add(p);
        System.out.println("Player " + p.getId() + ": "+ name + " successfully added to the game!");
    }

    
}
