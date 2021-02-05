package convertercurrency.telegrambot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface MessageHandler extends Handler {
    List<BotApiMethod<?>> handleInputMessage(Message message);
}
