package convertercurrency.telegrambot.util;

import convertercurrency.telegrambot.handler.BankHandler;
import convertercurrency.telegrambot.handler.CurrencyHandler;
import convertercurrency.telegrambot.handler.StartHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static convertercurrency.telegrambot.entity.State.SELECT_BANK;
import static convertercurrency.telegrambot.entity.State.SELECT_CURRENCY;
import static convertercurrency.telegrambot.entity.State.START;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchHandlerTest {

    @Autowired
    private SearchHandler searchHandler;

    @Test
    public void handler_StartState_Search() {

        assertThat(searchHandler.findHandler(START).getClass())
                .isNotNull()
                .isEqualTo(StartHandler.class);
    }

    @Test
    public void handler_SelectCurrencyState_Search() {

        assertThat(searchHandler.findHandler(SELECT_CURRENCY).getClass())
                .isNotNull()
                .isEqualTo(CurrencyHandler.class);
    }

    @Test
    public void handler_SelectBankState_Search() {

        assertThat(searchHandler.findHandler(SELECT_BANK).getClass())
                .isNotNull()
                .isEqualTo(BankHandler.class);
    }
}
