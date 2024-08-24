package uz.pdp.namozVaqtlari;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class NamozVaqtlariInline {
    public static SendMessage sendPrayerTimesKeyboard(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("◀\uFE0F       ▶\uFE0F");
        backButton.setCallbackData("back");

        InlineKeyboardButton bomdod = new InlineKeyboardButton();
        bomdod.setText("Bomdod:  05:00");
        bomdod.setCallbackData("bomdod");

        InlineKeyboardButton peshin = new InlineKeyboardButton();
        peshin.setText("Peshin:  12:00");
        peshin.setCallbackData("peshin");

        InlineKeyboardButton asr = new InlineKeyboardButton();
        asr.setText("Asr:  15:00");
        asr.setCallbackData("asr");

        InlineKeyboardButton shom = new InlineKeyboardButton();
        shom.setText("Shom:  18:00");
        shom.setCallbackData("shom");

        InlineKeyboardButton xuftom = new InlineKeyboardButton();
        xuftom.setText("Huftom:  20:00");
        xuftom.setCallbackData("xuftom");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(bomdod);
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(peshin);
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(asr);
        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(shom);
        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(xuftom);
        List<InlineKeyboardButton> row6 = new ArrayList<>();
        row6.add(backButton);


        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        rows.add(row5);
        rows.add(row6);

        inlineKeyboardMarkup.setKeyboard(rows);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setReplyMarkup(inlineKeyboardMarkup);

        return message;
    }
}
