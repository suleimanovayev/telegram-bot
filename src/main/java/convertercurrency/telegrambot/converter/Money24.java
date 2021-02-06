package convertercurrency.telegrambot.converter;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.StringJoiner;

import static convertercurrency.telegrambot.AppConstants.HEADLINE_CURRENCY;
import static org.apache.commons.lang3.StringUtils.replace;

public class Money24 extends BankConverter {

    public static final String MONEY24_URL = "https://money24.kharkov.ua/";
    public static final String MONEY24_SELECTOR = "#popup-exchange-table #tab1 >*";
    private final static String DOLLAR = "Американский доллар";
    private final static String EURO = "Евро";
    private final static String RUBLE = "Российский рубль";
    private final static String HRYVNA = "/ Украинская гривна";
    private final static String USD = "USD";
    private final static String EUR = "EUR";
    private final static String RUR = "RUB";

    @SneakyThrows
    @Override
    public String convert(String currencyValue) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(HEADLINE_CURRENCY + "\n");

        Document document = Jsoup.connect(MONEY24_URL).get();
        Elements elements = document.select(MONEY24_SELECTOR);

        elements.stream()
                .map(Element::text)
                .filter(text ->
                        containsIgnoreCase(text, DOLLAR, EURO, RUBLE))
                .map(value -> value.replaceAll(HRYVNA, ""))
                .map(value -> replace(value, DOLLAR, USD))
                .map(value -> replace(value, EURO, EUR))
                .map(value -> replace(value, RUBLE, RUR))
                .filter(value -> containsIgnoreCase(value, currencyValue))
                .forEach(stringJoiner::add);

        return stringJoiner.toString();
    }
}
