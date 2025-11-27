package com.agk;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.agk.model.JeopardyQuestions;
import com.agk.model.QuestionItem;
import com.agk.parser.CSVParser;
import com.agk.parser.JSONParser;
import com.agk.parser.ParserContext;
import com.agk.parser.XMLParser;

public class ParserTest {

    @Test
    public void testCSVParser() throws Exception {
        CSVParser parser = new CSVParser();
        JeopardyQuestions jq = parser.parseFile();
        assertNotNull("CSV parse returned null", jq);
        List<QuestionItem> questions = jq.getQuestions();
        assertNotNull("CSV questions list is null", questions);
        assertTrue("CSV should contain at least one question", questions.size() > 0);
        QuestionItem q = questions.get(0);
        assertNotNull(q.getCategory());
        assertNotNull(q.getQuestion());
        assertNotNull(q.getOptions());
        assertNotNull(q.getAnswer());
    }

    @Test
    public void testJSONParser() throws Exception {
        JSONParser parser = new JSONParser();
        JeopardyQuestions jq = parser.parseFile();
        assertNotNull("JSON parse returned null", jq);
        List<QuestionItem> questions = jq.getQuestions();
        assertNotNull("JSON questions list is null", questions);
        assertTrue("JSON should contain at least one question", questions.size() > 0);
        QuestionItem q = questions.get(0);
        assertNotNull(q.getCategory());
        assertNotNull(q.getQuestion());
        assertNotNull(q.getOptions());
        assertNotNull(q.getAnswer());
    }

    @Test
    public void testXMLParser() throws Exception {
        XMLParser parser = new XMLParser();
        JeopardyQuestions jq = parser.parseFile();
        assertNotNull("XML parse returned null", jq);
        List<QuestionItem> questions = jq.getQuestions();
        assertNotNull("XML questions list is null", questions);
        assertTrue("XML should contain at least one question", questions.size() > 0);
        QuestionItem q = questions.get(0);
        assertNotNull(q.getCategory());
        assertNotNull(q.getQuestion());
        assertNotNull(q.getOptions());
        assertNotNull(q.getAnswer());
    }

    @Test
    public void testParserContext() throws Exception {
        ParserContext context = new ParserContext();
        
        context.setParserStrategy(new CSVParser());
        JeopardyQuestions jq = context.parse();
        assertNotNull(jq);
        assertTrue(jq.getQuestions().size() > 0);

        
        context.setParserStrategy(new JSONParser());
        JeopardyQuestions jqJ = context.parse();
        assertNotNull(jqJ);
        assertTrue(jqJ.getQuestions().size() > 0);

        
        context.setParserStrategy(new XMLParser());
        JeopardyQuestions jqX = context.parse();
        assertNotNull(jqX);
        assertTrue(jqX.getQuestions().size() > 0);
    }

}
