package convertercurrency.telegrambot.bank;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Converter {
    private BankConverter bankConverter;

    public void setBankConverter(BankConverter bankConverter) {
        this.bankConverter = bankConverter;
    }

    public String convertCurrency(String currencyValue) throws IOException {
        return bankConverter.convert(currencyValue);
    }
}
