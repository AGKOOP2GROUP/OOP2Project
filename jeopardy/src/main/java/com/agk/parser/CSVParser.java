//concrete implementation of the parser interface to parse a csv file
package com.agk.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.agk.model.JeopardyQuestions;
import com.agk.model.Options;
import com.agk.model.QuestionItem;


public class CSVParser implements ParserInterface {
    JeopardyQuestions jeopardyQuestions = new JeopardyQuestions();
    List<QuestionItem> questions = new ArrayList<>(); 

    public CSVParser() {}

    @Override
    public JeopardyQuestions parseFile() {
        String csvFile = "jeopardy\\src\\main\\resources\\sample_game_CSV.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(csvFile));
            line = reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");     //split each line at the commas
                QuestionItem questionItem = new QuestionItem();
                questionItem.setCategory(row[0]);
                String val = row[1];
                int valInt = Integer.parseInt(val); //change string value to an int
                questionItem.setValue(valInt);
                questionItem.setQuestion(row[2]);
                Options options = new Options();
                options.setOptionA(row[3]);
                options.setOptionB(row[4]);
                options.setOptionC(row[5]);
                options.setOptionD(row[6]);
                questionItem.setOptions(options);
                questionItem.setAnswer(row[7]);
                /*
                System.out.println("Category: " + questionItem.getCategory());
                System.out.println("Value: " + questionItem.getValue());
                System.out.println("Question: " + questionItem.getQuestion());
                System.out.println("Options: ");
                //for (String option : questionItem.getOptions()) {
                //    System.out.println(option);
                //}
                System.out.println(questionItem.getOptions().getOptionA());
                System.out.println(questionItem.getOptions().getOptionB());
                System.out.println(questionItem.getOptions().getOptionC());
                System.out.println(questionItem.getOptions().getOptionD());
                System.out.println("Answer: " + questionItem.getAnswer());
                
                System.out.println();
                */
                questions.add(questionItem);       //add to the list of questions
            }
            jeopardyQuestions.setQuestions(questions);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jeopardyQuestions;
    }
    
}
