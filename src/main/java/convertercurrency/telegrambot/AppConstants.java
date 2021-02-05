package convertercurrency.telegrambot;

public class AppConstants {
    public static final String CURRENCY_HANDLER_DESCRIPTION = "Выберите валюту, которую будем искать";
    public static final String BANK_HANDLER_DESCRIPTION = "Выберите источник данных:";
    public static final String START_DESCRIPTION = "Добрый день, %s!";
    public static final String DOLLAR_TEXT = "доллар";
    public static final String DOLLAR_QUERY = "/USD";
    public static final String EURO_TEXT = "евро";
    public static final String EURO_QUERY = "/EUR";
    public static final String RUBLE_TEXT = "российский рубль";
    public static final String RUBLE_QUERY = "/RU";
    public static final String OSCHAD_BANK_TEXT = "ощад банк";
    public static final String OSCHAD_BANK_QUERY = "/oschad_bank";
    public static final String PRIVAT_BANK_TEXT = "приват банк";
    public static final String PRIVAT_BANK_QUERY = "/privat_bank";
    public static final String MONEY24_TEXT = "https://money24.kharkov.ua";
    public static final String MONEY24_QUERY = "/money24";
    public static final String START_QUERY = "/start";
    public static final String HEADLINE_CURRENCY = "Валюта  Покупка  Продажа";

    public static final String PRIVAT_BANK_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    public static final String MONEY24_URL = "https://money24.kharkov.ua/";
    public static final String OSCHAD_BANK_URL = "https://minfin.com.ua/ua/company/oschadbank/currency/";
    public static final String MONEY24_SELECTOR = "#popup-exchange-table #tab1 >*";
    public static final String OSCHAD_BANK_SELECTOR = ".currency-data tbody >*";
}