package convertercurrency.telegrambot.mapper;

import convertercurrency.telegrambot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "surname", source = "lastName")
    @Mapping(target = "chatId", source = "id")
    User map(org.telegram.telegrambots.meta.api.objects.User user);
}
