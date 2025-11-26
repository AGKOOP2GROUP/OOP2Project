//Parser interface
package com.agk.parser;

import com.agk.model.JeopardyQuestions;

public interface ParserInterface {
    public JeopardyQuestions parseFile() throws Exception;  //abstract parse method
}
