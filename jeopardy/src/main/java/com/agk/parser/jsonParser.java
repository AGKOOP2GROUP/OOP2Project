package com.agk.parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.agk.JeopardyQuestions;
import com.agk.QuestionItem;

public class jsonParser implements ParserInterface{
    JeopardyQuestions jeopardyQuestions = new JeopardyQuestions();        
    List<QuestionItem> questions = new ArrayList<>();

    @Override
    public void parseFile() {
        String jsonFile = "sample_game_JSON.json";
        BufferedReader reader = null;
        String line ;
        String jsonStr = "";

        try {
            reader = new BufferedReader(new FileReader(jsonFile));
            while ((line = reader.readLine()) != null) {
                jsonStr += line;
                jsonStr += "\n";
            }
        } catch (Exception e) {}
        
        JSONArray jsonArr = new JSONArray(jsonStr);
        //System.out.println(jsonArr);

        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject object = jsonArr.getJSONObject(i);
            QuestionItem questionItem = new QuestionItem();
            questionItem.setCategory(object.getString("Category"));
            questionItem.setValue(object.getInt("Value"));
            questionItem.setQuestion(object.getString("Question"));

            JSONObject options = object.getJSONObject("Options");
            String[] optionsArr = new String[options.length()];
            optionsArr[0] = options.getString("A");
            optionsArr[1] = options.getString("B");
            optionsArr[2] = options.getString("C");
            optionsArr[3] = options.getString("D");
            questionItem.setOptions(optionsArr);

            questionItem.setAnswer(jsonArr.getJSONObject(i).getString("CorrectAnswer"));
            /*
            System.out.println(questionItem.getCategory());
            System.out.println(questionItem.getValue());
            System.out.println(questionItem.getQuestion());
            for (String option : questionItem.getOptions()) {
                System.out.println(option);
            }
            System.out.println(questionItem.getAnswer());
            System.out.println();
            */
            
            questions.add(questionItem);
        }
        jeopardyQuestions.setQuestions(questions);
        
    }
    
}
