package convertercurrency.telegrambot.util;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageUtil {

    public static SendMessage createMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}
