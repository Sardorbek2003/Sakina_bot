package uz.pdp.servicex;

import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.Koran;
import uz.pdp.util.FilePath;
import uz.pdp.util.JsonUtil;

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
    List<Koran> list = JsonUtil.readGson(FilePath.PATH_KORAN, new TypeReference<List<Koran>>() {});
    Koran quranKarimRoot = new Koran();
    for (Koran root: list){
        if (str.endsWith(root.getTransliteration())){
            quranKarimRoot = root;
        }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Koran.Verse verse: quranKarimRoot.getVerses()){
        stringBuilder.append(verse.id + "  " + verse.transliteration +"\n");
    }
    return String.valueOf(stringBuilder);
}
public static String getAraSurah(String str){
    List<Koran> list = JsonUtil.readGson(FilePath.PATH_KORAN, new TypeReference<List<Koran>>() {});
    Koran quranKarimRoot = new Koran();
    for (Koran root: list){
        if (str.endsWith(root.getTransliteration())){
            quranKarimRoot = root;
        }
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Koran.Verse verse: quranKarimRoot.getVerses()){
        stringBuilder.append(verse.id + "  " + verse.text +"\n");
    }
    return String.valueOf(stringBuilder);
}
}
