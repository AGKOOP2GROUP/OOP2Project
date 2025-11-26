//context for strategy design pattern
package com.agk.parser;

import com.agk.model.JeopardyQuestions;

public class ParserContext {
    private ParserInterface strategy;

    //set the concrete implementation of the parser to be used
    public void setParserStrategy(ParserInterface strategy) {
        this.strategy = strategy;
    }

    //parse file using the parser set and return the questions from the file
    public JeopardyQuestions parse() throws Exception {
        return strategy.parseFile();
    }
}
