package uz.nuriddin.telegrambotdemo.handler;

import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface InlineQueryHandler {
    void handle(InlineQuery inlineQuery, AbsSender sender);
}
