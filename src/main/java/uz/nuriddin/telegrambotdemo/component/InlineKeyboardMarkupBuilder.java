package uz.nuriddin.telegrambotdemo.component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InlineKeyboardMarkupBuilder {

    private final InlineKeyboardMarkup inlineKeyboardMarkup;
    private final List<List<InlineKeyboardButton>> rows;

    public InlineKeyboardMarkupBuilder() {
        this.inlineKeyboardMarkup = new InlineKeyboardMarkup();
        this.rows = new ArrayList<>();
    }

    public static InlineKeyboardMarkup emptyInlineMarkup() {
        return new InlineKeyboardMarkupBuilder().build();
    }

    public static InlineKeyboardButton button(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }

    public static InlineKeyboardButton paymentButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        button.setPay(true);
        return button;
    }

    public static InlineKeyboardButton buttonUrl(String text, String url) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setUrl(url);
        return button;
    }

    public static List<InlineKeyboardButton> buttons(Map<String, String> map) {
        List<InlineKeyboardButton> list = new ArrayList<>();
        map.forEach((key, value) ->
                list.add(InlineKeyboardButton.builder().text(value).callbackData(key).build())
        );
        return list;
    }

    public InlineKeyboardMarkupBuilder row(InlineKeyboardButton... buttons) {
        rows.add(List.of(buttons));
        return this;
    }

    public InlineKeyboardMarkupBuilder addRowButton(String text, String callbackData) {
        rows.add(List.of(button(text, callbackData)));
        return this;
    }

    public int rowSize() {
        return rows.size();
    }

    public InlineKeyboardMarkupBuilder rows(List<InlineKeyboardButton> buttons) {
        rows.addAll(buttons.stream().map(List::of).toList());
        return this;
    }

    public InlineKeyboardMarkup build() {
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }
}
