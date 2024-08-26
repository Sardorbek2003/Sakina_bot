package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.Root;
import uz.pdp.service.util.JsonUtil;
import uz.pdp.service.util.MonthlyPrayerTimesUtil;

import java.io.File;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrayerTimeInlineService {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String PATH = "file/namoz_vaqtlari.json";

    public static Root getTodayTimes() {
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        String[] split = str.split("-");
        String day = split[2];
        String month = split[1];
        return rootList(day, month);

    }

    private static Root rootList(String day, String month) {
        List<Root> roots = JsonUtil.readGson(PATH, new TypeReference<List<Root>>() {});
        Root todayRoot = new Root();
        for (Root root : roots) {
            if (root.getDay() == Integer.parseInt(day) && root.getMonth() == Integer.parseInt(month)) {
                todayRoot = root;
            }
        }
        return todayRoot;
    }


    public static SendMessage sendPrayerTimesKeyboard(long chatId) {
        MonthlyPrayerTimesUtil.write();
        Root root = getTodayTimes();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        InlineKeyboardButton backButton1 = new InlineKeyboardButton();
        InlineKeyboardButton backButton2 = new InlineKeyboardButton();
        backButton.setText("◀\uFE0F ");
        backButton1.setText(" ▶\uFE0F ");
        backButton.setCallbackData("back");
        backButton1.setCallbackData("next");
        String monthName = new DateFormatSymbols().getMonths()[root.getMonth() - 1];
        backButton2.setText(root.getDay() + " - " + monthName);
        backButton2.setCallbackData("date");

        InlineKeyboardButton bomdod = new InlineKeyboardButton();
        bomdod.setText("Bomdod:  " + root.getTimes().getTong_saharlik());
        bomdod.setCallbackData("bomdod");

        InlineKeyboardButton peshin = new InlineKeyboardButton();
        peshin.setText("Peshin:  " + root.getTimes().getPeshin());
        peshin.setCallbackData("peshin");

        InlineKeyboardButton asr = new InlineKeyboardButton();
        asr.setText("Asr:  " + root.getTimes().getAsr());
        asr.setCallbackData("asr");

        InlineKeyboardButton shom = new InlineKeyboardButton();
        shom.setText("Shom:  " + root.getTimes().getShom_iftor());
        shom.setCallbackData("shom");

        InlineKeyboardButton xuftom = new InlineKeyboardButton();
        xuftom.setText("Huftom:  " + root.getTimes().getHufton());
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
        row6.add(backButton2);
        row6.add(backButton1);


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
