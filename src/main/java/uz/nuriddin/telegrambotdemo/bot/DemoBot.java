package uz.nuriddin.telegrambotdemo.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.nuriddin.telegrambotdemo.config.TelegramBotProperties;
import uz.nuriddin.telegrambotdemo.handler.CallbackQueryHandler;
import uz.nuriddin.telegrambotdemo.handler.MessageHandler;

@Slf4j
@Component
public class DemoBot extends TelegramLongPollingBot {

    private final TelegramBotProperties botProperties;
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    public DemoBot(MessageHandler messageHandler, CallbackQueryHandler callbackQueryHandler, TelegramBotProperties botProperties, DefaultBotOptions defaultBotOptions) {
        super(defaultBotOptions, botProperties.getToken());
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.botProperties = botProperties;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            messageHandler.handle(update.getMessage(), this);
        }else if (update.hasCallbackQuery()) {
            callbackQueryHandler.handle(update.getCallbackQuery(), this);
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

}
