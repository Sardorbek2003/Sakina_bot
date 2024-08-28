package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.QuranIndexRoot;
import uz.pdp.model.QuranKarimRoot;
import uz.pdp.model.Verse;
import uz.pdp.service.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class GetSurahUrl {
    public static SendMessage QuranMenyu(long chatId,String data){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        inlineKeyboardButton.setText("Lotin alifbosida ");
        inlineKeyboardButton.setCallbackData("l" + data);
        inlineKeyboardButton1.setText("باللغة العربية");
        inlineKeyboardButton1.setCallbackData("a" + data);
        buttons.add(inlineKeyboardButton1);
        buttons.add(inlineKeyboardButton);
        list.add(buttons);

        inlineKeyboardMarkup.setKeyboard(list);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Tilni Tanlang");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
public static String getLotinSurah(String str){
    List<QuranKarimRoot> list = JsonUtil.readGson("BotService/src/test/file/surah.json", new TypeReference<List<QuranKarimRoot>>() {});
    QuranKarimRoot quranKarimRoot = new QuranKarimRoot();
    for (QuranKarimRoot root: list){
        if (str.endsWith(root.getTransliteration())){
            quranKarimRoot = root;
        }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Verse verse: quranKarimRoot.getVerses()){
        stringBuilder.append(verse.id + "  " + verse.transliteration +"\n");
    }
    return String.valueOf(stringBuilder);
}
public static String getAraSurah(String str){
    List<QuranKarimRoot> list = JsonUtil.readGson("BotService/src/test/file/surah.json", new TypeReference<List<QuranKarimRoot>>() {});
    QuranKarimRoot quranKarimRoot = new QuranKarimRoot();
    for (QuranKarimRoot root: list){
        if (str.endsWith(root.getTransliteration())){
            quranKarimRoot = root;
        }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Verse verse: quranKarimRoot.getVerses()){
        stringBuilder.append(verse.id + "  " + verse.text +"\n");
    }
    return String.valueOf(stringBuilder);
}
}
