package uz.pdp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import uz.pdp.model.SurahDigitState;
import uz.pdp.model.Surahs;
import uz.pdp.util.BotUtil;
import uz.pdp.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class KoranBotService {
    private static List<Surahs> surahs = new ArrayList<>();
    private static List<SurahDigitState> surahsState = new ArrayList<>();

    public SendMessage getSurah(long chatId) {
        SendMessage sendMessagec = new SendMessage();
        sendMessagec.setChatId(chatId);
        sendMessagec.setText("Surahs");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup = getsurah(0);
        sendMessagec.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessagec;
    }

    public EditMessageReplyMarkup getSurah(long chatId, Integer messageId) {
        EditMessageReplyMarkup editMessage = new EditMessageReplyMarkup();
        editMessage.setChatId(chatId);
        editMessage.setMessageId(messageId);
        ObjectUtil.koranService.setSurahsState(surahsState);
        editMessage.setReplyMarkup(getsurah(getIndex(chatId)));
        return editMessage;
    }

    private int getIndex(long chatId) {
        surahsState = ObjectUtil.koranService.getSurahsState();
        for (int i = 0; i < surahsState.size(); i++) {
            if (surahsState.get(i).getChatId() == chatId) {
                surahsState.get(i).setId(surahsState.get(i).getId() + 10);
                ObjectUtil.koranService.setSurahsState(surahsState);
                return surahsState.get(i).getId();
            }
        }
        return 0;
    }

    private InlineKeyboardMarkup getsurah(int n) {
        surahs = ObjectUtil.koranService.getSurahs();
        List<Surahs> surah = ObjectUtil.koranService.getStringList(surahs, n);
        InlineKeyboardMarkup inlinedKeyboardMarkup = BotUtil.inlineKeyboardMarkup(surah, 1);
        return inlinedKeyboardMarkup;
    }

}
