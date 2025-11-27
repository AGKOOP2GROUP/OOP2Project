package com.agk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.agk.Gameplay;
import com.agk.model.JeopardyQuestions;
import com.agk.model.GameEvent;
import com.agk.logger.EventObserver;

import java.util.ArrayList;
import java.util.List;

public class EventLoggingTests {

    private Gameplay gameplay;
    private JeopardyQuestions questions;

    @BeforeEach
    void setup() {
        // Initialize gameplay and questions
        questions = new JeopardyQuestions(); // Ensure this constructor exists
        gameplay = new Gameplay(questions); // Ensure Gameplay can take JeopardyQuestions
    }

    @Test
    void testEventLogging() {
        TestLoggerObserver observer = new TestLoggerObserver();
        gameplay.addObserver(observer); // Assuming you have registerObserver method

        // Trigger some events in gameplay
        gameplay.play();
        gameplay.addPlayer("Alice");
        
        List<GameEvent> events = observer.getEvents();
        assertFalse(events.isEmpty(), "Events should be logged");
    }

    // Inner class for testing observer
    private static class TestLoggerObserver implements EventObserver {

        private final List<GameEvent> events = new ArrayList<>();

        @Override
        public void update(GameEvent event) {
            events.add(event);
        }

        public List<GameEvent> getEvents() {
            return events;
        }
    }
}
