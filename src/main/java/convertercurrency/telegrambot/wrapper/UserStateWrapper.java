package convertercurrency.telegrambot.wrapper;

import convertercurrency.telegrambot.entity.State;
import convertercurrency.telegrambot.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserStateWrapper {
    private User user;
    private State state;
}
