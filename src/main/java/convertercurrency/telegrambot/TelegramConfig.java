package convertercurrency.telegrambot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.telegram.bot")
@Component
@Getter
@Setter
@NoArgsConstructor
public class TelegramConfig {
    private String name;
    private String token;
}
