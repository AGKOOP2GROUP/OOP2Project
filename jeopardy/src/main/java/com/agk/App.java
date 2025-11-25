package com.agk;

import com.agk.parser.ParserContext;
import com.agk.parser.XMLParser;

public class App 
{
    public static void main( String[] args )
    {
        ParserContext context = new ParserContext();
        context.setParserStrategy(new XMLParser());
        try {
            context.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
