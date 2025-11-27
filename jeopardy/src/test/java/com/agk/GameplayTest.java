package com.agk;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.agk.model.GameEvent;
import com.agk.model.JeopardyQuestions;
import com.agk.model.QuestionItem;
import com.agk.model.TurnRecord;
import com.agk.parser.CSVParser;
import com.agk.parser.ParserContext;


public class GameplayTest {

	@Test
	public void testAddPlayerAndObserverNotification() {
		JeopardyQuestions questions = new JeopardyQuestions();
		Gameplay gameplay = new Gameplay(questions);

		final GameEvent[] received = new GameEvent[1];
		gameplay.addObserver(event -> received[0] = event);

		gameplay.addPlayer("Alice");

		List<String> players = gameplay.getPlayers();
		assertEquals(1, players.size());
		assertEquals("Alice", players.get(0));

		assertNotNull("Observer should have received an event", received[0]);
		assertEquals("Enter Player Name", received[0].getActivity());
		assertEquals("Alice", received[0].getPlayerId());
		assertEquals("Added", received[0].getResult());
	}

	@Test
	public void testCorrectAnswerScore() throws Exception {
		ParserContext context = new ParserContext();
		context.setParserStrategy(new CSVParser());
		JeopardyQuestions jq = context.parse();

		Gameplay gp = new Gameplay(jq);
		gp.addPlayer("Bob");

		QuestionItem firstQ = jq.getQuestions().stream().filter(q -> q.getCategory().equals("variables & data types") && q.getValue() == 100).findFirst().orElse(null);

		assertNotNull(firstQ);

		// Mark question as used and manually record the turn
		firstQ.setUsed(true);
		gp.getGameResult().addTurn(
			new TurnRecord("Bob", firstQ.getCategory(), firstQ.getValue(), firstQ.getQuestion(), firstQ.getAnswer(), true, firstQ.getValue(), firstQ.getValue()));

		assertEquals(100, gp.getGameResult().getTurns().get(0).scoreAfterTurn);
		assertEquals(1, gp.getGameResult().getTurns().size());
		assertTrue(gp.getGameResult().getTurns().get(0).correct);
	}

	@Test
	public void testIncorrectAnswerScore() throws Exception {
		ParserContext ctx = new ParserContext();
		ctx.setParserStrategy(new CSVParser());
		JeopardyQuestions jq = ctx.parse();

		Gameplay gp = new Gameplay(jq);
		gp.addPlayer("Bobby");

		QuestionItem firstQ = jq.getQuestions().stream().filter(q -> q.getCategory().equals("variables & data types") && q.getValue() == 100).findFirst().orElse(null);

		assertNotNull(firstQ);

		// Mark question as used and record incorrect answer
		firstQ.setUsed(true);
		gp.getGameResult().addTurn(
			new com.agk.model.TurnRecord(
				"Bobby",
				firstQ.getCategory(),
				firstQ.getValue(),
				firstQ.getQuestion(),
				"B", 
				false, 
				0, 
				-firstQ.getValue() 
			)
		);

		assertEquals(-100, gp.getGameResult().getTurns().get(0).scoreAfterTurn);
		assertEquals(1, gp.getGameResult().getTurns().size());
		assertFalse(gp.getGameResult().getTurns().get(0).correct);
	}

	@Test
	public void testQuestionNotRepeated() throws Exception {
		ParserContext context = new ParserContext();
		context.setParserStrategy(new CSVParser());
		JeopardyQuestions jq = context.parse();

		List<QuestionItem> allQuestions = jq.getQuestions();
		assertNotNull(allQuestions);
		assertTrue(!allQuestions.isEmpty());

		QuestionItem firstQuestion = allQuestions.get(0);
		assertFalse(firstQuestion.isUsed());

		firstQuestion.setUsed(true);
		assertTrue(firstQuestion.isUsed());

		List<QuestionItem> availableAfterUse = jq.getQuestions().stream()
				.filter(q -> !q.isUsed() && 
						q.getCategory().equals(firstQuestion.getCategory()) && 
						q.getValue() == firstQuestion.getValue())
				.toList();

		assertEquals(0, availableAfterUse.size());

	}

}
