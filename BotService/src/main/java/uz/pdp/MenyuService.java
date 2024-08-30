package uz.pdp;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MenyuService {
    public static SendMessage showMenyu(Long chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> list = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Namoz Vaqtlari\uD83D\uDD58"));
        keyboardRow.add(new KeyboardButton("Eng Yaqin Namoz\uD83D\uDCAC"));
        keyboardRow.add(new KeyboardButton("Eng yaqin Masjidlar\uD83D\uDD4C"));
        list.add(keyboardRow);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Tasbex\uD83D\uDCFF"));
        keyboardRow1.add(new KeyboardButton("Quroni Karimdan Bazi Suralar\uD83D\uDCD6"));
        keyboardRow1.add(new KeyboardButton("Muhim Duolar\uD83E\uDD32"));
        list.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton("Xijriy Yil Xisobi\uD83D\uDCC6"));
        keyboardRow2.add(new KeyboardButton("Islomiy Kitoblar\uD83D\uDCDA"));
        list.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(list);

        sendMessage.setText("Welcome Bot");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;

    }
}