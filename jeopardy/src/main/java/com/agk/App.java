package com.agk;

import com.agk.parser.csvParser;
import com.agk.parser.jsonParser;
import com.agk.parser.xmlParser;

public class App 
{
    public static void main( String[] args )
    {
        xmlParser parser = new xmlParser();
       parser.parseFile(); 

       csvParser csvparser = new csvParser();
       csvparser.parseFile();

       jsonParser jsonparser = new jsonParser();
       jsonparser.parseFile();
    }
}
