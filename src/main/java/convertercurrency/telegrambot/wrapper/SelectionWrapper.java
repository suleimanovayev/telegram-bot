package convertercurrency.telegrambot.wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class SelectionWrapper {
    private String currency;
    private String bank;
}
