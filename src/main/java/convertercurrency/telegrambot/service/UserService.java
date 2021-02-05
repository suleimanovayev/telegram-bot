package convertercurrency.telegrambot.service;

import convertercurrency.telegrambot.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByChatId(Long chatId);

    User save(User user);

    User update(User user);

    void deleteByChatId(Long chatId);

    User saveIfNotExist(User user);
}
