package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.Koran;
import uz.pdp.util.BotUtil;
import uz.pdp.util.FilePath;
import uz.pdp.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class GetSurahUrl {
    public static SendMessage quranMenyu(long chatId, String data) {

        List<String> stringDate = List.of("Lotin alifbosida", "باللغة العربية","BACK" , "NEXT");
        List<String> stringCallbackQuaryDate = List.of("l" + data, "a" + data, "next","back");

        InlineKeyboardMarkup inlineKeyboardMarkup1 = BotUtil.inlineKeyboardMarkup(stringDate, stringCallbackQuaryDate, 2);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Tilni Tanlang");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup1);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        return sendMessage;
    }

    public static String getLotinSurah(String str) {
        List<Koran> list = JsonUtil.readGson(FilePath.PATH_KORAN, new TypeReference<List<Koran>>() {});
        Koran quranKarimRoot = new Koran();
        for (Koran root : list) {
            if (str.endsWith(root.getTransliteration())) {
                quranKarimRoot = root;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Koran.Verse verse : quranKarimRoot.getVerses()) {
            stringBuilder.append(verse.id + "  " + verse.transliteration + "\n");
        }
        return String.valueOf(stringBuilder);
    }

    public static String getAraSurah(String str) {
        List<Koran> list = JsonUtil.readGson(FilePath.PATH_KORAN, new TypeReference<List<Koran>>() {
        });
        Koran quranKarimRoot = new Koran();
        for (Koran root : list) {
            if (str.endsWith(root.getTransliteration())) {
                quranKarimRoot = root;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Koran.Verse verse : quranKarimRoot.getVerses()) {
            stringBuilder.append(verse.id + "  " + verse.text + "\n");
        }
        return String.valueOf(stringBuilder);
    }
}
