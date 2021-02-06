package convertercurrency.telegrambot.converter;

import org.springframework.stereotype.Component;

@Component
public class Converter {
    private BankConverter bankConverter;

    public void setBankConverter(BankConverter bankConverter) {
        this.bankConverter = bankConverter;
    }

    public String convertCurrency(String currencyValue) {
        return bankConverter.convert(currencyValue);
    }
}
