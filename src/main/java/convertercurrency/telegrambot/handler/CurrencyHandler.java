package convertercurrency.telegrambot.handler;

import convertercurrency.telegrambot.annotation.HandlerState;
import convertercurrency.telegrambot.entity.State;
import convertercurrency.telegrambot.util.KeyboardButtonUtil;
import convertercurrency.telegrambot.util.SendMessageUtil;
import convertercurrency.telegrambot.wrapper.SelectionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static convertercurrency.telegrambot.AppConstants.CURRENCY_HANDLER_DESCRIPTION;
import static convertercurrency.telegrambot.AppConstants.DOLLAR_QUERY;
import static convertercurrency.telegrambot.AppConstants.DOLLAR_TEXT;
import static convertercurrency.telegrambot.AppConstants.EURO_QUERY;
import static convertercurrency.telegrambot.AppConstants.EURO_TEXT;
import static convertercurrency.telegrambot.AppConstants.RUBLE_QUERY;
import static convertercurrency.telegrambot.AppConstants.RUBLE_TEXT;
import static convertercurrency.telegrambot.entity.State.SELECT_BANK;
import static convertercurrency.telegrambot.entity.State.SELECT_CURRENCY;

@Component
@HandlerState(handlerState = SELECT_CURRENCY)
public class CurrencyHandler implements MessageHandler, CallBackQueryHandler {
    private final SelectionWrapper selectionWrapper;
    private final BankHandler bankHandler;
    private State nextState;

    @Autowired
    public CurrencyHandler(BankHandler bankHandler, SelectionWrapper selectionWrapper) {
        this.bankHandler = bankHandler;
        this.selectionWrapper = selectionWrapper;
        this.nextState = SELECT_CURRENCY;
    }

    @Override
    public List<BotApiMethod<?>> handleInputMessage(Message message) {
        SendMessage sendMessage = SendMessageUtil.createMessage(message.getChatId().toString(), CURRENCY_HANDLER_DESCRIPTION);

        List<InlineKeyboardButton> keyboardButtons = List.of(
                KeyboardButtonUtil.createInlineKeyboardButton(DOLLAR_TEXT, DOLLAR_QUERY),
                KeyboardButtonUtil.createInlineKeyboardButton(EURO_TEXT, EURO_QUERY),
                KeyboardButtonUtil.createInlineKeyboardButton(RUBLE_TEXT, RUBLE_QUERY)
        );

        List<List<InlineKeyboardButton>> keyboardButtonsList = List.of(keyboardButtons);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboardButtonsList);

        sendMessage.setReplyMarkup(markup);
        nextState = SELECT_BANK;
        return List.of(sendMessage);
    }

    @Override
    public State nextState() {
        return nextState;
    }

    @Override
    public List<BotApiMethod<?>> handleCallBackQuery(CallbackQuery callbackQuery) {
        String query = callbackQuery.getData();
        if (query != null) {
            selectionWrapper.setCurrency(query);
            nextState = SELECT_BANK;
            return bankHandler.handleInputMessage(callbackQuery.getMessage());
        }
        throw new IllegalArgumentException(String.format("No exist callbackQuery: %s", callbackQuery.toString()));
    }
}
