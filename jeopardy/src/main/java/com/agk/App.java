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
        if (strat.equals("json")) {
            context.setParserStrategy(new JSONParser());
        } else if (strat.equals("csv")) {
            context.setParserStrategy(new CSVParser());
        } else if (strat.equals("xml")) {
            context.setParserStrategy(new XMLParser());
        } else {
            System.out.println("Invalid input, defaulting to XML parser.");
            context.setParserStrategy(new XMLParser());
        }

        try {
            JeopardyQuestions questions = context.parse();
            Gameplay gameplay = new Gameplay(questions);
           // System.out.println("Loaded categories: " + gameplay.getCategories());
            System.out.print("Enter the number of players: ");
            int num = scanner.nextInt();
            scanner.nextLine();
            if(num > 4){
                System.out.println("The maximumm number of players is 4. Enter another number: ");
                num = scanner.nextInt();
            }else {
                for (int i = 0; i<num; i++){
                    System.out.print("Enter player name: ");
                    String player = scanner.nextLine();
                    gameplay.addPlayer(player);
                }
            } 
            System.out.println(gameplay.getPlayers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
