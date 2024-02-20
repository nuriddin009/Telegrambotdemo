package uz.nuriddin.telegrambotdemo.handler.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import uz.nuriddin.telegrambotdemo.handler.MessageHandler;

@Service
public class MessageHandlerImpl implements MessageHandler {
    @SneakyThrows
    @Override
    public void handle(Message message, AbsSender absSender) {
        absSender.execute(
                new SendMessage(message.getChatId().toString(),
                        message.getText()));

    }
}
