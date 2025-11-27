//Main class that controls the running of the game
package com.agk;

import java.time.LocalDateTime;
import java.util.Scanner;


import com.agk.exporter.DOCXReportGenerator;
import com.agk.exporter.PDFReportGenerator;
import com.agk.exporter.ReportGenerator;
import com.agk.exporter.TXTReportGenerator;
import com.agk.logger.ProcessMiningLogger;
import com.agk.model.JeopardyQuestions;
import com.agk.model.TurnRecord;
import com.agk.parser.CSVParser;
import com.agk.parser.JSONParser;
import com.agk.parser.ParserContext;
import com.agk.parser.XMLParser;
import com.agk.model.GameEvent;
import com.agk.model.GameResult;


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

            ProcessMiningLogger logger = ProcessMiningLogger.getInstance();
            gameplay.addObserver(logger);

     

            
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


            ////////////////////////////////////////
            
        System.out.println("Which reports would you like to generate?");
        System.out.println("Options: txt, pdf, docx, all, none");
        String reportChoice = scanner.nextLine().trim().toLowerCase();

        boolean generateTXT = false;
        boolean generatePDF = false;
        boolean generateDOCX = false;

        // Validate input
        switch (reportChoice) {
            case "txt":
                generateTXT = true;
                break;
            case "pdf":
                generatePDF = true;
                break;
            case "docx":
                generateDOCX = true;
                break;
            case "all":
                generateTXT = true;
                generatePDF = true;
                generateDOCX = true;
                break;
            case "none":
                System.out.println("No reports will be generated.");
                break;
            default:
                System.out.println("Invalid input, defaulting to TXT report only.");
                generateTXT = true;
                break;
        }

    // Generate reports based on selection
        ReportGenerator generator;
        GameResult result = gameplay.getGameResult();
        

        try {
            if (generateTXT) {
                ReportGenerator txtGenerator = new TXTReportGenerator();
                txtGenerator.generateReport(result);
                System.out.println("TXT report generated successfully!");

                gameplay.notifyObservers(new GameEvent(
                    result.getCaseId(), "System", "Generate TXT Report", LocalDateTime.now(),
                    "TXT", 0, "", "Success", 0
                ));
            }

            if (generatePDF) {
                ReportGenerator pdfGenerator = new PDFReportGenerator("jeopardy_game.pdf");
                pdfGenerator.generateReport(result);
                System.out.println("PDF report generated successfully!");

                gameplay.notifyObservers(new GameEvent(
                    result.getCaseId(), "System", "Generate PDF Report", LocalDateTime.now(),
                    "PDF", 0, "", "Success", 0
                ));
            }

            if (generateDOCX) {
                ReportGenerator docxGenerator = new DOCXReportGenerator();
                docxGenerator.generateReport(result);
                System.out.println("DOCX report generated successfully!");

                gameplay.notifyObservers(new GameEvent(
                    result.getCaseId(), "System", "Generate DOCX Report", LocalDateTime.now(),
                    "DOCX", 0, "", "Success", 0
                ));
            }

        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }

       

        gameplay.notifyObservers(new GameEvent(
            result.getCaseId(), "System", "Generate Event Log", LocalDateTime.now(),
            "CSV", 0, "", "Success", 0
        ));
         logger.saveToCSV("game_event_log.csv");


        //    generator.generateReport(gameplay.getGameResult());
            
            /////////////////////////////////////////


           //System.out.println(gameplay.getPlayers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

     
