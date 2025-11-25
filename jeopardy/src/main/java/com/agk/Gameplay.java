package com.agk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.agk.model.JeopardyQuestions;
import com.agk.model.Player;

public class Gameplay {
    private String gameId;
    private List<Player> players = new ArrayList<>();
    private final JeopardyQuestions jeopardyQuestions;
    private int currPlayer = 0;
    Scanner scanner = new Scanner(System.in);

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

    public void play(Gameplay gameplay){
        while(!finished()){
            Player curr = players.get(currPlayer);
            System.out.println("Player " + curr.getUsername() + "'s turn!");

            System.out.println("Choose a category from the following or enter 'quit' to quit: " + gameplay.getCategories());
            String category = scanner.nextLine().trim().toLowerCase();
            if (category.equals("quit")){
                System.out.println("Thank you for playing!");
                break;
            }
            if (!((gameplay.getCategories()).contains(category))){
                System.out.println("Invalid category, try again: ");
                
            }
        }
        
        
    }

    private boolean finished(){
        return false;
    }
}
