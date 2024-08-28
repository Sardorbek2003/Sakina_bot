package uz.pdp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.QuranIndexRoot;
import uz.pdp.model.SuraIndex;
import uz.pdp.service.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class QuroniKarimdanBaziSuralar {
    public static SendMessage getSurah(long chatId) {
        SendMessage sendMessagec = new SendMessage();
        sendMessagec.setChatId(chatId);
        sendMessagec.setText("surah");
        int n = 0;
        List<SuraIndex> suraIndices = JsonUtil.readGson("BotService/src/test/file/suraIndex.json", new TypeReference<List<SuraIndex>>() {});
        for (SuraIndex index : suraIndices) {
            if (index.getChatId() == chatId) {
                n = index.getId();
            }
        }
        JsonUtil.writeGson(suraIndices, "BotService/src/test/file/suraIndex.json");
        sendMessagec.setReplyMarkup(getsurah(n));
        return sendMessagec;
    }

    public static EditMessageReplyMarkup getSurah(long chatId, Integer messageId) {
        EditMessageReplyMarkup editMessage = new EditMessageReplyMarkup();
        editMessage.setChatId(chatId);
        editMessage.setMessageId(messageId);
        int n = 0;
        List<SuraIndex> suraIndices = JsonUtil.readGson("BotService/src/test/file/suraIndex.json", new TypeReference<List<SuraIndex>>() {
        });
        for (SuraIndex index : suraIndices) {
            if (index.getChatId() == chatId) {
                n = index.getId();
            }
        }
        JsonUtil.writeGson(suraIndices, "BotService/src/test/file/suraIndex.json");
        editMessage.setReplyMarkup(getsurah(n));
        return editMessage;
    }

    private static InlineKeyboardMarkup getsurah(int n) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<QuranIndexRoot> roots = JsonUtil.readGson("BotService/src/test/file/quranSuralariIndex.json", new TypeReference<List<QuranIndexRoot>>() {});
        for (int i = n - 10; i < n && i <= 114; i++) {
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            String text = roots.get(i).transliteration;
            String link = roots.get(i).link;
            int index = roots.get(i).id;
            inlineKeyboardButton.setText(String.valueOf(index) + "   " + text);
            inlineKeyboardButton.setCallbackData("suralar" + text);
            buttons.add(inlineKeyboardButton);
            list.add(buttons);
        }
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        inlineKeyboardButton.setText("NEXT");
        inlineKeyboardButton.setCallbackData("next");
        inlineKeyboardButton1.setText("BACK");
        inlineKeyboardButton1.setCallbackData("back");
        buttons.add(inlineKeyboardButton1);
        buttons.add(inlineKeyboardButton);
        list.add(buttons);

        inlineKeyboardMarkup.setKeyboard(list);
        return inlineKeyboardMarkup;
    }

}
