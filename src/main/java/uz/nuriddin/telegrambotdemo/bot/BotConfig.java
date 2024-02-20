package uz.nuriddin.telegrambotdemo.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.nuriddin.telegrambotdemo.config.TelegramBotProperties;
import uz.nuriddin.telegrambotdemo.handler.CallbackQueryHandler;
import uz.nuriddin.telegrambotdemo.handler.MessageHandler;

@Component
@RequiredArgsConstructor
public class BotConfig {

    private final TelegramBotProperties botProperties;
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setMaxThreads(10);
        return options;
    }

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new DemoBot(messageHandler, callbackQueryHandler, botProperties, new DefaultBotOptions()));
        return telegramBotsApi;
    }
}
