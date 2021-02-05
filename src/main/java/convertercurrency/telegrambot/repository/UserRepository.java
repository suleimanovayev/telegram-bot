package convertercurrency.telegrambot.repository;

import convertercurrency.telegrambot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByChatId(Long chatId);

    void deleteByChatId(Long chatId);
}
