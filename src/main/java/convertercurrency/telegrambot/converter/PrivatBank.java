package convertercurrency.telegrambot.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static convertercurrency.telegrambot.AppConstants.HEADLINE_CURRENCY;

public class PrivatBank extends BankConverter {
    public static final String PRIVAT_BANK_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final String BASE_CCY = "base_ccy";
    private static final String CCY = "ccy";
    private static final String UAH = "UAH";

    @SneakyThrows
    @Override
    public String convert(String currencyValue) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(HEADLINE_CURRENCY + "\n");

        URL url = new URL(PRIVAT_BANK_URL);

        List<Map<String, String>> values =
                new ObjectMapper().readValue(url, new TypeReference<>() {
                });

        values.stream()
                .filter(map -> containsIgnoreCase(map.get(BASE_CCY), UAH))
                .filter(map -> containsIgnoreCase(map.get(CCY), currencyValue))
                .flatMap(map -> map.values().stream())
                .filter(value -> !containsIgnoreCase(value, UAH))
                .forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
