package uz.nuriddin.telegrambotdemo.handler.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import uz.nuriddin.telegrambotdemo.bot.DataLoader;
import uz.nuriddin.telegrambotdemo.handler.MessageHandler;
import uz.nuriddin.telegrambotdemo.payload.Address;
import uz.nuriddin.telegrambotdemo.payload.Company;
import uz.nuriddin.telegrambotdemo.payload.User;

import java.util.Arrays;
import java.util.List;

@Slf4j
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
            }else {

                sender.execute(DeleteMessage.builder()
                                .chatId(chatId)
                                .messageId(message.getMessageId())
                        .build());

                try {
                    int id = Integer.parseInt(text);

                    List<User> list = Arrays.stream(DataLoader.users).filter(user -> user.getId() == id).toList();

                    if (!list.isEmpty()) {
                        User user = list.get(0);
                        Address address = user.getAddress();
                        Company company = user.getCompany();
                        sender.execute(SendMessage.builder()
                                .chatId(chatId)
                                .text("\uD83D\uDC64 Name: " + user.getName() + "\n" +
                                        "\uD83D\uDCF1 Phone: " + user.getPhone() + "\n" +
                                        "\uD83D\uDCE7 Email: " + user.getEmail() + "\n" +
                                        "\uD83D\uDD11 Username: " + user.getUsername() + "\n" +
                                        "\uD83D\uDCCD Address: " + address.getCity() + ", " + address.getStreet() + ", " + address.getSuite() + ", " + address.getZipcode() + "\n" +
                                        "\uD83C\uDFE2 Company: " + company.getName() + ", " + company.getCatchPhrase() + "\n" +
                                        "\uD83C\uDF10 Website: " + user.getWebsite()
                                )
                                .build());

                    }else {
                        sender.execute(SendMessage.builder()
                                .chatId(chatId)
                                .text("User topilmadi!!")
                                .build());
                    }

                } catch (NumberFormatException e) {
                    log.error(e.getMessage());
                }


            }


        }else if (message.hasContact()) {
            Contact contact = message.getContact();

        }

    }
}
