package uz.nuriddin.telegrambotdemo.bot;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.nuriddin.telegrambotdemo.config.TelegramBotProperties;
import uz.nuriddin.telegrambotdemo.handler.CallbackQueryHandler;
import uz.nuriddin.telegrambotdemo.handler.InlineQueryHandler;
import uz.nuriddin.telegrambotdemo.handler.MessageHandler;

@Slf4j
@NoArgsConstructor(force = true)
@Component
public class DemoBot extends TelegramLongPollingBot {

    private final TelegramBotProperties botProperties;
    private final MessageHandler messageHandler;
    private final CallbackQueryHandler callbackQueryHandler;
    private final InlineQueryHandler inlineQueryHandler;

    public DemoBot(MessageHandler messageHandler,
                   CallbackQueryHandler callbackQueryHandler,
                   TelegramBotProperties botProperties,
                   DefaultBotOptions defaultBotOptions, InlineQueryHandler inlineQueryHandler) {
        super(defaultBotOptions, botProperties.getToken());
        this.messageHandler = messageHandler;
        this.callbackQueryHandler = callbackQueryHandler;
        this.botProperties = botProperties;
        this.inlineQueryHandler = inlineQueryHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            messageHandler.handle(update.getMessage(), this);
        }else if (update.hasCallbackQuery()) {
            callbackQueryHandler.handle(update.getCallbackQuery(), this);
        }else if (update.hasInlineQuery()) {
            inlineQueryHandler.handle(update.getInlineQuery(), this);
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }

}
