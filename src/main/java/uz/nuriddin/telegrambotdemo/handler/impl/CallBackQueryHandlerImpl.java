package uz.nuriddin.telegrambotdemo.handler.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;
import uz.nuriddin.telegrambotdemo.handler.CallbackQueryHandler;

@Service
public class CallBackQueryHandlerImpl implements CallbackQueryHandler {
    @Override
    public void handle(CallbackQuery callbackQuery, AbsSender absSender) {

    }
}
