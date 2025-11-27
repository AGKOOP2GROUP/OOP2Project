package com.agk.logger;

import com.agk.model.GameEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessMiningLogger implements EventObserver {
    private static ProcessMiningLogger instance;
    private List<GameEvent> events;

    private ProcessMiningLogger() {
        events = new ArrayList<>();
    }

    public static ProcessMiningLogger getInstance() {
        if (instance == null) {
            instance = new ProcessMiningLogger();
        }
        return instance;
    }

    @Override
    public void update(GameEvent event) {
        events.add(event);
        // System.out.println("Event logged: " + event.getActivity());
    }

    public void saveToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play\n");
            for (GameEvent e : events) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%d\n",
                        e.getCaseId(), e.getPlayerId(), e.getActivity(), e.getTimestamp(),
                        e.getCategory(), e.getQuestionValue(), e.getAnswerGiven(),
                        e.getResult(), e.getScoreAfter()));
            }
            System.out.println("CSV saved: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
