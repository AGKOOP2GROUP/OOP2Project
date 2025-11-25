package com.agk.model;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class QuestionItem {
    @JacksonXmlProperty(localName = "Category")
    private String category;
    @JacksonXmlProperty(localName = "Value")
    private int value;
    @JacksonXmlProperty(localName = "QuestionText")
    private String question;
    @JacksonXmlProperty(localName = "Options")
    private String[] options;
    @JacksonXmlProperty(localName = "CorrectAnswer")
    private String answer;

    public QuestionItem(){}


    public String getCategory(){
        return category.toLowerCase();
    }  

    public int getValue(){
        return value;
    }

    public String getQuestion(){
        return question;
    }

    public String[] getOptions(){
        return options;
    }

    public String getAnswer(){
        return answer;
    }
    
    public void setCategory(String category){
        this.category = category;
    }

    public void setValue(int value){
        this.value = value;
    }   

    public void setQuestion(String question){
        this.question = question;
    }

    public void setOptions(String[] options){
        this.options = options;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
    
}
