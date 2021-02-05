package convertercurrency.telegrambot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@EnableConfigurationProperties({
        TelegramConfig.class
})
@Component
@RequiredArgsConstructor
public class CurrencyConverterBot extends TelegramLongPollingBot {
    private final TelegramConfig telegramConfig;
    private final UpdateReceiver updateReceiver;

    @Override
    public String getBotUsername() {
        return telegramConfig.getName();
    }

    @Override
    public String getBotToken() {
        return telegramConfig.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        List<BotApiMethod<?>> messagesToSend = updateReceiver.handle(update);

        if (messagesToSend != null) {
            messagesToSend.forEach(this::executeMessage);
        }
    }

    private void executeMessage(BotApiMethod<?> sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
