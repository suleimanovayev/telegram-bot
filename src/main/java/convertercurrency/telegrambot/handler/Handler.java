package convertercurrency.telegrambot.handler;

import convertercurrency.telegrambot.entity.State;

public interface Handler {

    State nextState();
}
