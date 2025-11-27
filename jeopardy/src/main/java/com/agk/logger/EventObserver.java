package com.agk.logger;

import com.agk.model.GameEvent;

public interface EventObserver {
    void update(GameEvent event);
}
