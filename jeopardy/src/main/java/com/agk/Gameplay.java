//Handles the actual logic behind each round of the game
package com.agk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.agk.model.JeopardyQuestions;
import com.agk.model.Player;
import com.agk.model.QuestionItem;

public class Gameplay {
    //private String gameId;
    private List<Player> players = new ArrayList<>();
    private final JeopardyQuestions jeopardyQuestions;
    private int currPlayer = 0;
    Scanner scanner = new Scanner(System.in);

    public Gameplay(JeopardyQuestions jeopardyQuestions) {  //constructor
        this.jeopardyQuestions = jeopardyQuestions;
    }

    //getters
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
    //add a player to this game
    public void addPlayer(String name){
        if (name == null)
            return;
        Player p = new Player(players.size() + 1, name);    //create player. id is determined by the order in which players join
        players.add(p);
        System.out.println("Player " + p.getId() + ": "+ name + " successfully added to the game!");    //success message
    }

    public void play(){     //start game
        List<QuestionItem> questions = getJeopardyQuestions().getQuestions();
        System.out.println();
        System.out.println("Welcome to Jeopardy! The game begins now.");
        //logic for one round of the game.
        while(!finished()){      
            System.out.println();
            Player player = players.get(currPlayer);    //current player for this round 
            System.out.println("Player " + player.getUsername() + "'s turn!");

            //to allow player to choose a category
            System.out.println("Choose a category from the following " + this.getCategories() + " or enter 'quit' to end game: " );
            String category = scanner.nextLine().trim().toLowerCase();
            System.out.println();
            if (category.equals("quit")){   //end game
                System.out.println("Thank you for playing!");
                break;
            }
            if (!((this.getCategories()).contains(category))){  //if category does not exist
                System.out.println("Invalid category");
                continue;
            }
            
            //to allow player to choose a question value
            ArrayList<Integer> values = new ArrayList<>();
            for (QuestionItem q: questions){    //get values for the chosen category
                if (q.getCategory().equals(category))
                    values.add(q.getValue());
            }
            System.out.println("Choose a question value from the following: " + values);
            String valueStr = scanner.nextLine();
            System.out.println();
            int value;
            //check if value entered is a number
            try { 
                value = Integer.parseInt(valueStr); 
            } catch 
            (NumberFormatException e) {     //if invalid, move on to another player
                System.out.println("Invalid value"); 
                continue;
            }
            if (!(values.contains(value))){
                System.out.println("Invalid value");
                continue;
            }

            //filter questions by category and value to find the chosen question
            QuestionItem chosenQ = questions.stream().filter(q -> q.getCategory().equals(category) && q.getValue() == value).findFirst().orElse(null);
            if (chosenQ == null){
                System.out.println("Invalid question");
                continue;
            }
            //print options for that question
            System.out.println("Question: " + chosenQ.getQuestion());
            System.out.println("A. " + chosenQ.getOptions().getOptionA());
            System.out.println("B. " + chosenQ.getOptions().getOptionB());
            System.out.println("C. " + chosenQ.getOptions().getOptionC());
            System.out.println("D. " + chosenQ.getOptions().getOptionD());

            //allow user to answer 
            System.out.print("Choose an answer: ");
            String selectedAnswer = scanner.nextLine().toUpperCase();
            if(selectedAnswer.equals(chosenQ.getAnswer())){     //if their answer is correct, add their points to their score
                player.updateScore(value);
                System.out.println("Correct! You gained " + value + " points.");
            }
            else {      //if their answer is incorrect, minus the points from their score
                player.updateScore(-(value));
                System.out.println("Inorrect :( , you lost " + value + " points.");
            }
            //display the player's current score
            System.out.println("Player " + player.getUsername() + "'s current score: " + player.getScore());

            System.out.println();
            currPlayer = (currPlayer + 1) % players.size();     //move on to the next player

        }
        
        
    }

    private boolean finished(){
        return false;
    }
}
