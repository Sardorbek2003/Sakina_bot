package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.PrayerTime;
import uz.pdp.util.BotUtil;
import uz.pdp.util.FilePath;
import uz.pdp.util.JsonUtil;
import uz.pdp.servicex.MonthlyPrayerTimesUtil;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrayerTimeInlineService {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getToday() {
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        String[] split = str.split("-");
        String day = split[2];
        return day;
    }

    public static String getMonth() {
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        String[] split = str.split("-");
        String month = split[1];
        return month;
    }

    private static PrayerTime root() {
        String month = getMonth();
        String day = getToday();
        List<PrayerTime> roots = JsonUtil.readGson(FilePath.PATH_NAMOZ_VAQTLARI, new TypeReference<>() {
        });
        PrayerTime todayRoot = new PrayerTime();
        for (PrayerTime root : roots) {
            if (root.getDay() == Integer.parseInt(day) && root.getMonth() == Integer.parseInt(month)) {
                todayRoot = root;
            }
        }
        return todayRoot;
    }

    public static SendMessage sendPrayerTimesKeyboard(long chatId) {
        MonthlyPrayerTimesUtil.write();
        PrayerTime root = root();
        String monthName = new DateFormatSymbols().getMonths()[root.getMonth() - 1];

        List<String> date = List.of("Bomdod:  " + root.getTimes().getTong_saharlik(),
                "Peshin:  " + root.getTimes().getPeshin(), "Asr:  " + root.getTimes().getAsr(), "Shom:  " + root.getTimes().getShom_iftor(),
                "Huftom:  " + root.getTimes().getHufton());
        List<String> calbackQuary = List.of("bomdod", "peshin", "asr", "shom", "xuftom");

        InlineKeyboardMarkup inlineKeyboardMarkup = BotUtil.inlineKeyboardMarkup(date, calbackQuary, 1);

        List<String> stringList = List.of("◀\uFE0F ", root.getDay() + " - " + monthName, " ▶\uFE0F ");
        List<String> backk = List.of("backk", "date", "nextt");
        InlineKeyboardMarkup inlineKeyboardMarkup1 = BotUtil.inlineKeyboardMarkup(stringList, backk, 3);

        List<List<InlineKeyboardButton>> keyboard = inlineKeyboardMarkup.getKeyboard();
        keyboard.add(inlineKeyboardMarkup1.getKeyboard().get(0));

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
