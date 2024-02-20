package uz.nuriddin.telegrambotdemo.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface CallbackQueryHandler {
    void handle(CallbackQuery callbackQuery, AbsSender absSender);
}
