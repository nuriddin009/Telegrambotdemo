package uz.nuriddin.telegrambotdemo.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Service
public class CallBackQueryHandlerImpl implements CallbackQueryHandler{
    @Override
    public void handle(CallbackQuery callbackQuery, AbsSender absSender) {

    }
}
