package uz.nuriddin.telegrambotdemo.handler;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface MessageHandler{
    void handle(Message message, AbsSender sender);
}



