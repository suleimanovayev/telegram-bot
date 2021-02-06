package convertercurrency.telegrambot.converter;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.StringJoiner;

import static convertercurrency.telegrambot.AppConstants.HEADLINE_CURRENCY;

public class OschadBank extends BankConverter {
    public static final String OSCHAD_BANK_URL = "https://minfin.com.ua/ua/company/oschadbank/currency/";
    public static final String OSCHAD_BANK_SELECTOR = ".currency-data tbody >*";

    @SneakyThrows
    @Override
    public String convert(String currencyValue) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(HEADLINE_CURRENCY + "\n");

        Document document = Jsoup.connect(OSCHAD_BANK_URL).get();
        Elements elements = document.select(OSCHAD_BANK_SELECTOR);

        elements.stream()
                .limit(3)
                .map(Element::text)
                .filter(text -> containsIgnoreCase(text, currencyValue))
                .flatMap(text -> Arrays.stream(text.split(" ")))
                .limit(5)
                .forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
