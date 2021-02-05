package convertercurrency.telegrambot.service.impl;

import convertercurrency.telegrambot.entity.User;
import convertercurrency.telegrambot.repository.UserRepository;
import convertercurrency.telegrambot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByChatId(Long chatId) {
        return userRepository.findByChatId(chatId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteByChatId(Long chatId) {
        userRepository.deleteByChatId(chatId);
    }

    @Override
    public User saveIfNotExist(User user) {
        return findByChatId(user.getChatId())
                .orElseGet(() -> userRepository.save(user));
    }
}
