package uz.nuriddin.telegrambotdemo.handler.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;
import uz.nuriddin.telegrambotdemo.constants.Callback;
import uz.nuriddin.telegrambotdemo.handler.CallbackQueryHandler;

@Service
public class CallBackQueryHandlerImpl implements CallbackQueryHandler {
    @Override
    public void handle(CallbackQuery callbackQuery, AbsSender sender) {
        Callback callback = Callback.valueOf(callbackQuery.getData());
        String chatId = callbackQuery.getFrom().getId().toString();

        switch (callback) {
            case START -> System.out.println("Start");
            case PHONE -> System.out.println("Phone");
            case LOCATION -> System.out.println("Location");
            default -> throw new RuntimeException();
        }

    }
}
