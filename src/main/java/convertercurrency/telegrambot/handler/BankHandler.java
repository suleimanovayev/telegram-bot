package convertercurrency.telegrambot.handler;

import convertercurrency.telegrambot.annotation.HandlerState;
import convertercurrency.telegrambot.entity.State;
import convertercurrency.telegrambot.converter.Converter;
import convertercurrency.telegrambot.converter.Money24;
import convertercurrency.telegrambot.converter.OschadBank;
import convertercurrency.telegrambot.converter.PrivatBank;
import convertercurrency.telegrambot.util.KeyboardButtonUtil;
import convertercurrency.telegrambot.util.SendMessageUtil;
import convertercurrency.telegrambot.wrapper.SelectionWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static convertercurrency.telegrambot.AppConstants.BANK_HANDLER_DESCRIPTION;
import static convertercurrency.telegrambot.AppConstants.MONEY24_QUERY;
import static convertercurrency.telegrambot.AppConstants.MONEY24_TEXT;
import static convertercurrency.telegrambot.AppConstants.OSCHAD_BANK_QUERY;
import static convertercurrency.telegrambot.AppConstants.OSCHAD_BANK_TEXT;
import static convertercurrency.telegrambot.AppConstants.PRIVAT_BANK_QUERY;
import static convertercurrency.telegrambot.AppConstants.PRIVAT_BANK_TEXT;
import static convertercurrency.telegrambot.entity.State.SELECT_BANK;
import static convertercurrency.telegrambot.entity.State.START;
import static java.lang.String.valueOf;

@Component
@HandlerState(handlerState = SELECT_BANK)
public class BankHandler implements MessageHandler, CallBackQueryHandler {
    private final SelectionWrapper selectionWrapper;
    private final Converter converter;
    private State nextState;

    @Autowired
    public BankHandler(SelectionWrapper selectionWrapper, Converter converter) {
        this.selectionWrapper = selectionWrapper;
        this.converter = converter;
        this.nextState = SELECT_BANK;
    }

    @Override
    public List<BotApiMethod<?>> handleInputMessage(Message message) {
        SendMessage sendMessage = SendMessageUtil.createMessage(message.getChatId().toString(), BANK_HANDLER_DESCRIPTION);

        List<InlineKeyboardButton> keyboardButtons = List.of(
                KeyboardButtonUtil.createInlineKeyboardButton(OSCHAD_BANK_TEXT, OSCHAD_BANK_QUERY),
                KeyboardButtonUtil.createInlineKeyboardButton(PRIVAT_BANK_TEXT, PRIVAT_BANK_QUERY),
                KeyboardButtonUtil.createInlineKeyboardButton(MONEY24_TEXT, MONEY24_QUERY)
        );

        List<List<InlineKeyboardButton>> keyboardButtonsList = List.of(keyboardButtons);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboardButtonsList);

        sendMessage.setReplyMarkup(markup);
        return List.of(sendMessage);
    }

    @SneakyThrows
    @Override
    public List<BotApiMethod<?>> handleCallBackQuery(CallbackQuery callbackQuery) {
        String bankQuery = callbackQuery.getData();
        String currencyValue = selectionWrapper.getCurrency().replace("/", "");

        switch (bankQuery) {
            case PRIVAT_BANK_QUERY:
                converter.setBankConverter(new PrivatBank());
                break;
            case OSCHAD_BANK_QUERY:
                converter.setBankConverter(new OschadBank());
                break;
            case MONEY24_QUERY:
                converter.setBankConverter(new Money24());
        }

        String text = converter.convertCurrency(currencyValue);
        SendMessage sendMessage = SendMessageUtil.createMessage(valueOf(callbackQuery.getMessage().getChatId()), text);
        nextState = START;
        return List.of(sendMessage);
    }

    @Override
    public State nextState() {
        return nextState;
    }
}
