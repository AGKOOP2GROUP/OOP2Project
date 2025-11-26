//concrete implementation of the parser interface for reading json file
package com.agk.parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.agk.model.JeopardyQuestions;
import com.agk.model.Options;
import com.agk.model.QuestionItem;

public class JSONParser implements ParserInterface{
    JeopardyQuestions jeopardyQuestions = new JeopardyQuestions();        
    List<QuestionItem> questions = new ArrayList<>();

    @Override
    public JeopardyQuestions parseFile() {
        String jsonFile = "jeopardy\\src\\main\\resources\\sample_game_JSON.json";
        BufferedReader reader = null;
        String line ;
        String jsonStr = "";

        try {
            reader = new BufferedReader(new FileReader(jsonFile));
            while ((line = reader.readLine()) != null) {    //read whole file line by line and store in a string
                jsonStr += line;
                jsonStr += "\n";
            }
        } catch (Exception e) {}
        
        JSONArray jsonArr = new JSONArray(jsonStr);     //convert the string into a JSONArray 
        //System.out.println(jsonArr);

        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);   //get JSONObject within array
            QuestionItem questionItem = new QuestionItem();     //create a new question item
            questionItem.setCategory(object.getString("Category"));
            questionItem.setValue(object.getInt("Value"));
            questionItem.setQuestion(object.getString("Question"));

            JSONObject options = object.getJSONObject("Options");
            Options op = new Options();
            op.setOptionA(options.getString("A"));
            op.setOptionB(options.getString("B"));
            op.setOptionC(options.getString("C"));
            op.setOptionD(options.getString("D"));
            questionItem.setOptions(op);

            questionItem.setAnswer(jsonArr.getJSONObject(i).getString("CorrectAnswer"));
            /*
            System.out.println(questionItem.getCategory());
            System.out.println(questionItem.getValue());
            System.out.println(questionItem.getQuestion());
            System.out.println(questionItem.getOptions().getOptionA());
            System.out.println(questionItem.getOptions().getOptionB());
            System.out.println(questionItem.getOptions().getOptionC());
            System.out.println(questionItem.getOptions().getOptionD());
            System.out.println(questionItem.getAnswer());
            System.out.println();
            */
            
            questions.add(questionItem);      //add question item to list of questions
        }
        jeopardyQuestions.setQuestions(questions);
        return jeopardyQuestions;
        
    }
    
}
