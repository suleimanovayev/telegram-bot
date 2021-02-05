package convertercurrency.telegrambot.handler;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.List;

public interface CallBackQueryHandler extends Handler {

    List<BotApiMethod<?>> handleCallBackQuery(CallbackQuery callbackQuery);
}
