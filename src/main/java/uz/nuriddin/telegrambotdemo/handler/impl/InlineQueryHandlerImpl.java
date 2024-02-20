package uz.nuriddin.telegrambotdemo.handler.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.nuriddin.telegrambotdemo.handler.InlineQueryHandler;
import uz.nuriddin.telegrambotdemo.payload.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class InlineQueryHandlerImpl implements InlineQueryHandler {
    private static final Integer CACHETIME = 0;

    private static final String apiUrl = "https://jsonplaceholder.typicode.com/users";

    private User[] users = {};

    @PostConstruct
    public void getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        users = restTemplate.getForObject(apiUrl, User[].class);
    }

    @Override
    public void handle(InlineQuery inlineQuery, AbsSender sender) {
        handleIncomingInlineQuery(inlineQuery, sender);
    }

    private void handleIncomingInlineQuery(InlineQuery inlineQuery, AbsSender sender) {
        String query = inlineQuery.getQuery();
        log.debug("Searching: " + query);
        try {
            if (!query.isEmpty()) {
                User[] array = Arrays.stream(users).filter(user ->
                        user.getName().toLowerCase().contains(query.toLowerCase())
                ).toArray(User[]::new);
                sender.execute(convertResultsToResponse(inlineQuery, array));
            }else {
                sender.execute(convertResultsToResponse(inlineQuery, users));
            }
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private static AnswerInlineQuery convertResultsToResponse(InlineQuery inlineQuery, User[] results) {
        AnswerInlineQuery answerInlineQuery = new AnswerInlineQuery();
        answerInlineQuery.setInlineQueryId(inlineQuery.getId());
        answerInlineQuery.setCacheTime(CACHETIME);
        answerInlineQuery.setResults(convertResults(results));
        return answerInlineQuery;
    }

    private static List<InlineQueryResult> convertResults(User[] raeResults) {
        List<InlineQueryResult> results = new ArrayList<>();

        for (int i = 0; i < raeResults.length; i++) {
            User user = raeResults[i];
            InputTextMessageContent messageContent = new InputTextMessageContent();
            messageContent.setMessageText(user.getId() + " " + user.getName());
            InlineQueryResultArticle article = new InlineQueryResultArticle();
            article.setInputMessageContent(messageContent);
            article.setId(Integer.toString(i));
            article.setTitle(user.getName());
            article.setDescription(
                    "Email: " + user.getEmail() + "\n" +
                            "Phone: " + user.getPhone() + "\n" +
                            "Username: " + user.getUsername() + "\n" +
                            "Company: " + user.getCompany().getName() + "\n" +
                            "Address: " + user.getAddress().getCity() + ", " + user.getAddress().getStreet() + ", " + user.getAddress().getZipcode()
            );
//            article.setThumbUrl(THUMBNAILBLUE);
            results.add(article);
        }

        return results;
    }
}
