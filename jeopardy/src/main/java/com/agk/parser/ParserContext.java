package com.agk.parser;

import com.agk.model.JeopardyQuestions;

public class ParserContext {
    private ParserInterface strategy;

    public void setParserStrategy(ParserInterface strategy) {
        this.strategy = strategy;
    }

    public JeopardyQuestions parse() throws Exception {
        return strategy.parseFile();
    }
}
