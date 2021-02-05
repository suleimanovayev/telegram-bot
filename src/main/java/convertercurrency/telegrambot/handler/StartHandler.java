package convertercurrency.telegrambot.handler;

import convertercurrency.telegrambot.annotation.HandlerState;
import convertercurrency.telegrambot.entity.State;
import convertercurrency.telegrambot.util.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

import static convertercurrency.telegrambot.AppConstants.START_DESCRIPTION;
import static convertercurrency.telegrambot.AppConstants.START_QUERY;
import static convertercurrency.telegrambot.entity.State.SELECT_CURRENCY;
import static convertercurrency.telegrambot.entity.State.START;

@Component
@HandlerState
public class StartHandler implements MessageHandler {
    private final CurrencyHandler currencyHandler;
    private State nextState;

    @Autowired
    public StartHandler(CurrencyHandler currencyHandler) {
        this.currencyHandler = currencyHandler;
        nextState = START;
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public List<BotApiMethod<?>> handleInputMessage(Message message) {
        List<BotApiMethod<?>> sendMessageList = new ArrayList<>();
        String command = message.getText();
        if (command.equals(START_QUERY)) {
            String helloMessage = String.format(START_DESCRIPTION, message.getFrom().getFirstName());
            SendMessage welcomeMessage = SendMessageUtil.createMessage(message.getChatId().toString(), helloMessage);
            sendMessageList.add(welcomeMessage);
            sendMessageList.addAll(currencyHandler.handleInputMessage(message));
            nextState = SELECT_CURRENCY;
            return sendMessageList;
        }
        throw new IllegalArgumentException(String.format("Cannot read command: %s", command));
    }
}
