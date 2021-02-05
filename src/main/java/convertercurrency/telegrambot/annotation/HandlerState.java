package convertercurrency.telegrambot.annotation;

import convertercurrency.telegrambot.entity.State;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface HandlerState {

    State handlerState() default State.START;
}