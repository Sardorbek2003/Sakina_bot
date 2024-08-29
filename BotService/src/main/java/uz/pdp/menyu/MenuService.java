package uz.pdp.menyu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.util.BotUtil;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    public static SendMessage showMenyu(Long chatId) {
        List<String> list = new ArrayList<>();
        list.add("Namoz Vaqtlari\uD83D\uDD58");
        list.add("Eng Yaqin Namoz\uD83D\uDCAC");
        list.add("Eng yaqin Masjidlar\uD83D\uDD4C");
        list.add("Tasbex\uD83D\uDCFF");
        list.add("Quroni Karimdan Bazi Suralar\uD83D\uDCD6");
        list.add("Muhim Duolar\uD83E\uDD32");
        list.add("Xijriy Yil Xisobi\uD83D\uDCC6");
        list.add("Islomiy Kitoblar\uD83D\uDCDA");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = BotUtil.replyKeyboardMarkup(list,3);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setText("Welcome Bot");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;

    }
}