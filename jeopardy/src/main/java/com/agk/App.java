package com.agk;

import com.agk.parser.ParserContext;
import com.agk.parser.XMLParser;

public class App 
{
    public static void main( String[] args )
    {
       /* XMLParser parser = new XMLParser();
       parser.parseFile(); 

       CSVParser csvparser = new CSVParser();
       csvparser.parseFile();

       JSONParser jsonparser = new JSONParser();
       jsonparser.parseFile();*/

        ParserContext context = new ParserContext();
        context.setParserStrategy(new XMLParser());
        try {
            context.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
