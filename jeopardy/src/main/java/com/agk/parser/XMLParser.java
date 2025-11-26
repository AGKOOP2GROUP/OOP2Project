package com.agk.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import com.agk.model.JeopardyQuestions;
import com.agk.model.QuestionItem;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLParser implements ParserInterface {
    @Override
    public JeopardyQuestions parseFile (){
        try {
            XmlMapper mapper = new XmlMapper();
            InputStream input = new FileInputStream("jeopardy\\src\\main\\resources\\sample_game_XML.xml");
            JeopardyQuestions root = mapper.readValue(input, JeopardyQuestions.class);
            List<QuestionItem> questions = root.getQuestions();
            /*
            for (QuestionItem q : questions) {
                System.out.println("Category: " + q.getCategory());
                System.out.println("Value: " + q.getValue());
                System.out.println("Question: " + q.getQuestion());
                System.out.println("Options: ");
                //for (String option : q.getOptions()) {
                  //  System.out.println(option);
                //}
                System.out.println(q.getOptions().getOptionA());
                System.out.println(q.getOptions().getOptionB());
                System.out.println(q.getOptions().getOptionC());
                System.out.println(q.getOptions().getOptionD());
                System.out.println("Answer: " + q.getAnswer());
                System.out.println();
            }
            */
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
