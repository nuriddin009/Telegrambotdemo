package uz.nuriddin.telegrambotdemo.handler.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import uz.nuriddin.telegrambotdemo.handler.MessageHandler;

@Service
public class MessageHandlerImpl implements MessageHandler {
    @SneakyThrows
    @Override
    public void handle(Message message, AbsSender sender) {
        String chatId = message.getChatId().toString();
        org.telegram.telegrambots.meta.api.objects.User from = message.getFrom();

        if (message.hasText()) {
            String text = message.getText();
            String fullName = from.getFirstName() + " " + (from.getLastName() != null ? from
                    .getLastName() : "");
            if (text.equals("/start")) {
                sender.execute(
                        SendMessage.builder()
                                .chatId(chatId)
                                .text("Assalamu alaykum " + fullName + ". Botimizga xush kelibsiz!")
                                .build()
                );
            }


        }else if (message.hasContact()) {
            Contact contact = message.getContact();
        }

    }
}
