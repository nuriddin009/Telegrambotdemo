package uz.nuriddin.telegrambotdemo;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.nuriddin.telegrambotdemo.bot.DemoBot;

@SpringBootApplication
public class TelegrambotdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegrambotdemoApplication.class, args);
    }




}
