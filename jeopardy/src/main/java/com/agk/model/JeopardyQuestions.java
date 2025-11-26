//Data is parsed into here 
package com.agk.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class JeopardyQuestions {
    private List<QuestionItem> questions;

    public JeopardyQuestions() {
        this.questions = new ArrayList<>();
    }

    //get rid of wrapping element
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "QuestionItem")
    
    public List<QuestionItem> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionItem> questions) {
        this.questions = questions;
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        if (questions == null) 
            return categories;
        for (QuestionItem q : questions) {      //to check if category is already listed
            if (!categories.contains(q.getCategory())) {
                categories.add(q.getCategory());
            }
        }
        return categories;
    }

}
