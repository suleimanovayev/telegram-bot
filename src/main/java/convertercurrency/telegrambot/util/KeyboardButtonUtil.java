package convertercurrency.telegrambot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class KeyboardButtonUtil {

    public static InlineKeyboardButton createInlineKeyboardButton(String text, String callbackQuery) {
        InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
        keyboardButton.setText(text);
        keyboardButton.setCallbackData(callbackQuery);

        return keyboardButton;
    }
}
