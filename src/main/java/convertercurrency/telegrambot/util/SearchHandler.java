package convertercurrency.telegrambot.util;

import convertercurrency.telegrambot.annotation.HandlerState;
import convertercurrency.telegrambot.entity.State;
import convertercurrency.telegrambot.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SearchHandler {
    private final List<? extends Handler> handlers;

    public Handler findHandler(State state) {
        return handlers.stream()
                .filter(handler -> handler.getClass()
                        .isAnnotationPresent(HandlerState.class))
                .filter(handler -> Stream.of(handler.getClass()
                        .getAnnotation(HandlerState.class)
                        .handlerState())
                        .anyMatch(handlerState -> handlerState.equals(state)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not exist handlers with state: %s", state)));
    }
}
