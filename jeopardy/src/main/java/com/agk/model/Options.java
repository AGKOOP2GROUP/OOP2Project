package com.agk.model;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Options {
    @JacksonXmlProperty(localName = "OptionA")
    private String optionA;
    @JacksonXmlProperty(localName = "OptionB")
    private String optionB;
    @JacksonXmlProperty(localName = "OptionC")
    private String optionC;
    @JacksonXmlProperty(localName = "OptionD")
    private String optionD;

    public Options (){}

    public String getOptionA(){
        return optionA;
    }

    public String getOptionB(){
        return optionB;
    }

    public String getOptionC(){
        return optionC;
    }

    public String getOptionD(){
        return optionD;
    }

    public void setOptionA(String optionA){
        this.optionA = optionA;
    }

    public void setOptionB(String optionB){
        this.optionB = optionB;
    }

    
    public void setOptionC(String optionC){
        this.optionC = optionC;
    }

    public void setOptionD(String optionD){
        this.optionD = optionD;
    }
}
