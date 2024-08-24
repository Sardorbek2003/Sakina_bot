package uz.pdp.namozVaqtlari;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class NamozVaqtlari {
    public static SendMessage getNamozVatlari(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> list = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Namoz vaqtlari\uD83D\uDD52"));
        list.add(keyboardRow);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Back⬅\uFE0F"));
        list.add(keyboardRow1);

        replyKeyboardMarkup.setKeyboard(list);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        
        return sendMessage;
    }
}
