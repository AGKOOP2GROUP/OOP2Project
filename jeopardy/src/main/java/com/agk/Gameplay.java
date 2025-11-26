//Handles the actual logic behind each round of the game
package com.agk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.agk.model.GameResult;
import com.agk.model.JeopardyQuestions;
import com.agk.model.Player;
import com.agk.model.QuestionItem;
import com.agk.model.TurnRecord;

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
    private GameResult result = new GameResult();

    public GameResult getGameResult() {
        return result;
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

            System.out.println();
            showAvailable(getCategories());
            System.out.println();

            //to allow player to choose a category
            System.out.println("Choose a category from the following " + jeopardyQuestions.getAvailableCategories() + " or enter 'quit' to end game: " );
            String category = scanner.nextLine().trim().toLowerCase();
            System.out.println();
            if (category.equals("quit")){   //end game
                System.out.println("Thank you for playing!");
                break;
            }
            if (!((jeopardyQuestions.getAvailableCategories()).contains(category))){  //if category does not exist
                System.out.println("Invalid category");
                continue;
            }
            
            //to allow player to choose a question value
            ArrayList<Integer> values = new ArrayList<>();
            for (QuestionItem q: questions){    //get values for the chosen category
                if (q.getCategory().equals(category) && !q.isUsed())
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
            QuestionItem chosenQ = questions.stream().filter(q -> q.getCategory().equals(category) && q.getValue() == value && !(q.isUsed())).findFirst().orElse(null);
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
            boolean correct = true;
            int pointsEarned = 0; 
            if(selectedAnswer.equals(chosenQ.getAnswer())){     //if their answer is correct, add their points to their score
                player.updateScore(value);
                System.out.println("Correct! You gained " + value + " points.");
                pointsEarned = value;
                correct = true;
            }
            else {      //if their answer is incorrect, minus the points from their score
                player.updateScore(-(value));
                System.out.println("Inorrect :( , you lost " + value + " points.");
                correct = false;
                pointsEarned = 0;
            }
            chosenQ.setUsed(true);   //so question will not be asked again

            
            //display the player's current score
            System.out.println("Player " + player.getUsername() + "'s current score: " + player.getScore());
            System.out.println("-----------------------------------------------------------------------------------------");


            TurnRecord rec = new TurnRecord(
                    player.getUsername(),
                    category,
                    value,
                    chosenQ.getQuestion(),
                    selectedAnswer,
                    correct,
                    pointsEarned,
                    player.getScore()       // scoreAfterTurn
            );

            result.addTurn(rec);




            System.out.println();
            currPlayer = (currPlayer + 1) % players.size();     //move on to the next player
            result.setFinalPlayers(new ArrayList<>(players));


        }
        
        
    }

    private boolean finished(){
        //System.out.println("No more questions available. Thank you for playing!");
        return jeopardyQuestions.getQuestions().stream().allMatch(QuestionItem::isUsed);
    }

    private void showAvailable(List<String> categories){ // display board with categories and values available
        System.out.println("AVAILABLE QUESTIONS");
        System.out.println("---------------------");

        List<Integer> allVal = new ArrayList<>();
        allVal.add(100);
        allVal.add(200);
        allVal.add(300);
        allVal.add(400);
        allVal.add(500);

        System.out.print(String.format("%-30s", "Category")); //category heading
        for (Integer val : allVal){
            System.out.print(String.format("%-10s", val));//value headings

        }  
        System.out.println();

        for(String cat : categories){
            System.out.print(String.format("%-30s", cat));

            for (Integer val: allVal){
                QuestionItem qu = jeopardyQuestions.getQuestions().stream().filter(q -> q.getCategory().equals(cat) && q.getValue() == val).findFirst().orElse(null);
                if (qu == null){
                    System.out.print(String.format("%-10s", " "));
                }
                else {
                    if (qu.isUsed()){
                        System.out.print(String.format("%-10s", "X"));
                    }
                    else {
                        System.out.print(String.format("%-10s", val));
                    }
                }
            }
            System.out.println();
        }       
    }
}
