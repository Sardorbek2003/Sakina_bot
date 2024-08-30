package uz.pdp.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyboardUtil {

    public static InlineKeyboardButton inlineKeyboardButton(String text,String qalbackQuery){
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(text);
        inlineKeyboardButton.setCallbackData(qalbackQuery);

        return inlineKeyboardButton;
    }

    public static List<InlineKeyboardButton> list(InlineKeyboardButton...  inlineKeyboardButton){
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        inlineKeyboardButtons.addAll(Arrays.asList(inlineKeyboardButton));
        return inlineKeyboardButtons;
    }

    public static List<List<InlineKeyboardButton>> listList(List<InlineKeyboardButton>... lists){
        List<List<InlineKeyboardButton>> listList = new ArrayList<>();
        listList.addAll(Arrays.asList(lists));
        return listList;
    }

    public static InlineKeyboardMarkup inlineKeyboardMarkup(List<List<InlineKeyboardButton>> listslist){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(listslist);
        return inlineKeyboardMarkup;
    }

    public static KeyboardButton keyboardButton(String text){
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(text);
        return keyboardButton;
    }

    public static KeyboardRow keyboardRow(KeyboardButton ... keyboardButton){
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(List.of(keyboardButton));
        return keyboardRow;
    }

    public static List<KeyboardRow> keyboardRowList(KeyboardRow... keyboardRows){
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.addAll(List.of(keyboardRows));
        return keyboardRowList;
    }

    public static ReplyKeyboardMarkup replyKeyboardMarkup(List<KeyboardRow> keyboardRowList){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
}
