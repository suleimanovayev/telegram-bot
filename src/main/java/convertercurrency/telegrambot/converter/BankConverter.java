package convertercurrency.telegrambot.converter;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public abstract class BankConverter {

    abstract String convert(String currencyValue);

    protected boolean containsIgnoreCase(CharSequence str, CharSequence... searchStr) {
        return Arrays.stream(searchStr).anyMatch(value -> StringUtils.containsIgnoreCase(str, value));
    }
}
