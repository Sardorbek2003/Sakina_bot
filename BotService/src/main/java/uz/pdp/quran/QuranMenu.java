package uz.pdp.quran;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class QuranMenu {

    public static SendMessage quronMenu(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows1 = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Afzal rofiqov (Qur'on tafsiri) audiosi "));
        rows1.add(keyboardRow);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton("Mishary ar-Roshid Alafasy"));
        rows1.add(keyboardRow1);

        keyboardRow1.add(new KeyboardButton("Mahmud Halil Husoriy"));
        rows1.add(keyboardRow1);

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(new KeyboardButton("Abu Bakir Ash-Shatri"));
        rows1.add(keyboardRow3);

        keyboardRow3.add(new KeyboardButton("Muhammadloiq Qori"));
        rows1.add(keyboardRow3);

        KeyboardRow keyboardRow5 = new KeyboardRow();
        keyboardRow5.add(new KeyboardButton("Maher Al Muaiqly"));
        rows1.add(keyboardRow5);

        keyboardRow5.add(new KeyboardButton("Muhammad Ayyub"));
        rows1.add(keyboardRow5);

        KeyboardRow keyboardRow7 = new KeyboardRow();
        keyboardRow7.add(new KeyboardButton("Muhammad Jibril"));
        rows1.add(keyboardRow7);

        keyboardRow7.add(new KeyboardButton("Yaser Al Dossary"));
        rows1.add(keyboardRow7);

        KeyboardRow keyboardRow9 = new KeyboardRow();
        keyboardRow9.add(new KeyboardButton("Shayx Muhammad Sodiq Muhammad Yusuf"));
        rows1.add(keyboardRow9);

        keyboardRow9.add(new KeyboardButton("Ali Al Huzaify"));
        rows1.add(keyboardRow9);

        KeyboardRow keyboardRow11 = new KeyboardRow();
        keyboardRow11.add(new KeyboardButton("back"));
        rows1.add(keyboardRow11);

        replyKeyboardMarkup.setKeyboard(rows1);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }

    public static SendMessage suralar(Long chatId){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = getKeyboardRowList();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    private static List<KeyboardRow> getKeyboardRowList() {
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow1 = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();
        KeyboardButton keyboardButton1 = new KeyboardButton();

        keyboardButton.setText("Quron suralari");
        keyboardRow.add(keyboardButton);

        keyboardRow.add(new KeyboardButton("Ma'ruzalar"));

        keyboardButton1.setText("Back");
        keyboardRow1.add(keyboardButton1);
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardRow1);
        return keyboardRows;
    }

    public static SendMessage suraName(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(""));

        replyKeyboardMarkup.setKeyboard(null);
        return sendMessage;
    }


}
