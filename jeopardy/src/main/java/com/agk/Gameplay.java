package com.agk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.agk.model.JeopardyQuestions;
import com.agk.model.Player;
import com.agk.model.QuestionItem;

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

    public void play(){
        System.out.println("Welcom to Jeopardy! The game begins now.");
        while(!finished()){
            Player player = players.get(currPlayer);
            System.out.println("Player " + player.getUsername() + "'s turn!");

            System.out.println("Choose a category from the following or enter 'quit' to quit: " + this.getCategories());
            String category = scanner.nextLine().trim().toLowerCase();
            if (category.equals("quit")){
                System.out.println("Thank you for playing!");
                break;
            }
            if (!((this.getCategories()).contains(category))){
                System.out.println("Invalid category");
                continue;
            }
            
            ArrayList<Integer> values = new ArrayList<>();
            List<QuestionItem> questions;
            questions = getJeopardyQuestions().getQuestions();
            for (QuestionItem q: questions){
                if (q.getCategory().equals(category))
                    values.add(q.getValue());
            }
            System.out.println("Choose a question value from the following: " + values);
            String valueStr = scanner.nextLine();
            int value;
            try { 
                value = Integer.parseInt(valueStr); 
            } catch 
            (NumberFormatException e) { 
                System.out.println("Invalid value"); 
                continue;
            }
            if (!(values.contains(value))){
                System.out.println("Invalid value");
                continue;
            }

            QuestionItem chosenQ = questions.stream().filter(q -> q.getCategory().equals(category) && q.getValue() == value).findFirst().orElse(null);
            if (chosenQ == null){
                System.out.println("Invalid question");
                continue;
            }
            System.out.println("Question: " + chosenQ.getQuestion());
            System.out.println("A. " + chosenQ.getOptions().getOptionA());
            System.out.println("B. " + chosenQ.getOptions().getOptionB());
            System.out.println("C. " + chosenQ.getOptions().getOptionC());
            System.out.println("D. " + chosenQ.getOptions().getOptionD());
            
            currPlayer = (currPlayer + 1) % players.size();

        }
        
        
    }

    private boolean finished(){
        return false;
    }
}
