package convertercurrency.telegrambot.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;
}