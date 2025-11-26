//Main class that controls the running of the game
package com.agk;

import java.util.Scanner;

import com.agk.model.JeopardyQuestions;
import com.agk.parser.CSVParser;
import com.agk.parser.JSONParser;
import com.agk.parser.ParserContext;
import com.agk.parser.XMLParser;

public class App 
{
    public static void main( String[] args )
    {
        ParserContext context = new ParserContext();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which file would you like to read? (xml/json/csv)");
        String strat = scanner.nextLine().toLowerCase();
        if (strat.equals("json")) {     //use concrete JSON parser 
            context.setParserStrategy(new JSONParser());
        } else if (strat.equals("csv")) {   //use concrete CSV parser
            context.setParserStrategy(new CSVParser());
        } else if (strat.equals("xml")) {   //use concrete XML parser
            context.setParserStrategy(new XMLParser());
        } else {    //if the user enters anything besides json/csv/xml, use the xml parser 
            System.out.println("Invalid input, defaulting to XML parser.");
            context.setParserStrategy(new XMLParser());
        }
        System.out.println();
        try {
            JeopardyQuestions questions = context.parse();  //read sample file using chosen parser
            Gameplay gameplay = new Gameplay(questions);    //create a new game
            
            //Request and accept number of players from user
            System.out.print("Enter the number of players (1-4): ");

            int num = scanner.nextInt();
            scanner.nextLine();
            
            //ensure number entered is within 1-4
            if (num < 5 && num > 0)
                num = num;
            else {
                System.out.print("If another invalid value is entered, a default will be set. Enter number of players: ");
                num = scanner.nextInt();
                scanner.nextLine();
                if(num > 4) {   //if number is too big, default to maximum capacity
                    num = 4;
                }else if (num < 1){//if number is too small, default to minimum capacity
                    num = 1;
                }
           
            }
            //Request and accept player names for each player 
            for (int i = 0; i<num; i++){
                System.out.println();
                System.out.print("Enter player name: ");
                String player = scanner.nextLine();
                gameplay.addPlayer(player);     //add each player to the list of players in the created game

            }

            gameplay.play(); //start game
            
           //System.out.println(gameplay.getPlayers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

