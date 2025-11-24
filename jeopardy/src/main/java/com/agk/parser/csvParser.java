package com.agk.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.agk.JeopardyQuestions;
import com.agk.QuestionItem;

public class csvParser implements ParserInterface {
    JeopardyQuestions jeopardyQuestions = new JeopardyQuestions();
    List<QuestionItem> questions = new ArrayList<>(); 

    @Override
    public void parseFile() {
        String csvFile = "sample_game_CSV.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(csvFile));
            line = reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                QuestionItem questionItem = new QuestionItem();
                questionItem.setCategory(row[0]);
                questionItem.setValue(row[1]);
                questionItem.setQuestion(row[2]);
                String[] options = {row[3], row[4], row[5], row[6]};
                questionItem.setOptions(options);
                questionItem.setAnswer(row[7]);
                /*
                System.out.println("Category: " + questionItem.getCategory());
                System.out.println("Value: " + questionItem.getValue());
                System.out.println("Question: " + questionItem.getQuestion());
                System.out.println("Options: ");
                for (String option : questionItem.getOptions()) {
                    System.out.println(option);
                }
                System.out.println("Answer: " + questionItem.getAnswer());
                
                System.out.println();*/
                questions.add(questionItem);
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
        
    }
    
}
