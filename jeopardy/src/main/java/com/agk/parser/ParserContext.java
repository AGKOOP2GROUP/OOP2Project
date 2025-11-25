package com.agk.parser;

public class ParserContext {
    private ParserInterface strategy;

    public void setParserStrategy(ParserInterface strategy) {
        this.strategy = strategy;
    }

    public void parse() throws Exception {
        strategy.parseFile();
    }
}
