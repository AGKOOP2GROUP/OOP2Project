//an individual question with its options and answer
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
    private Options options;
    @JacksonXmlProperty(localName = "CorrectAnswer")
    private String answer;

    public QuestionItem(){}

    //getters
    public String getCategory(){
        return category.toLowerCase();
    }  

    public int getValue(){
        return value;
    }

    public String getQuestion(){
        return question;
    }

    public Options getOptions(){
        return options;

    }

    public String getAnswer(){
        return answer;
    }
    
    //setters
    public void setCategory(String category){
        this.category = category;
    }

    public void setValue(int value){
        this.value = value;
    }   

    public void setQuestion(String question){
        this.question = question;
    }

    public void setOptions(Options options){
        this.options = options;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
    
}
