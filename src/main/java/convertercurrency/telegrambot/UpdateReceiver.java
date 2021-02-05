package convertercurrency.telegrambot;

import convertercurrency.telegrambot.entity.User;
import convertercurrency.telegrambot.handler.CallBackQueryHandler;
import convertercurrency.telegrambot.handler.MessageHandler;
import convertercurrency.telegrambot.mapper.UserMapper;
import convertercurrency.telegrambot.service.UserService;
import convertercurrency.telegrambot.util.SearchHandler;
import convertercurrency.telegrambot.wrapper.UserStateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

import static convertercurrency.telegrambot.entity.State.START;

@Component
public class UpdateReceiver {
    private final UserService userService;
    private final SearchHandler searchHandler;
    private final UserStateWrapper userStateWrapper;
    private final UserMapper userMapper;

    @Autowired
    public UpdateReceiver(UserService userService, SearchHandler searchHandler, UserStateWrapper userStateWrapper, UserMapper userMapper) {
        this.userService = userService;
        this.searchHandler = searchHandler;
        this.userStateWrapper = userStateWrapper;
        this.userMapper = userMapper;
    }

    public List<BotApiMethod<?>> handle(Update update) {
        List<BotApiMethod<?>> sendMessage = new ArrayList<>();
        Message message;

        if (hasMessageText(update)) {
            message = update.getMessage();
            checkIfStateIsNull();
            checkIfUserExist(message);
            MessageHandler messageHandler = (MessageHandler) searchHandler.findHandler(userStateWrapper.getState());
            sendMessage = messageHandler.handleInputMessage(message);
            userStateWrapper.setState(messageHandler.nextState());
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            CallBackQueryHandler callBackQueryHandler = (CallBackQueryHandler) searchHandler.findHandler(userStateWrapper.getState());
            sendMessage = callBackQueryHandler.handleCallBackQuery(callbackQuery);
            userStateWrapper.setState(callBackQueryHandler.nextState());
        }
        return sendMessage;
    }

    private void checkIfStateIsNull() {
        if (userStateWrapper.getState() == null) {
            userStateWrapper.setState(START);
        }
    }

    private void checkIfUserExist(Message message) {
        if (userService.findByChatId(message.getChatId()).isEmpty()) {
            User user = userMapper.map(message.getFrom());
            userService.saveIfNotExist(user);
        }
    }

    private boolean hasMessageText(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }
}
