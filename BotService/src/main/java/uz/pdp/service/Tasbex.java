package uz.pdp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Tasbex {
    private static int n = 0;
    public static SendMessage getTasbex(long chatId,String data){
        n = 0;
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();

        inlineKeyboardButton.setText(data + "\uD83D\uDCFF");
        inlineKeyboardButton.setCallbackData("tasbex");
        buttons.add(inlineKeyboardButton);
        list.add(buttons);
        inlineKeyboardMarkup.setKeyboard(list);

        sendMessage.setChatId(chatId);
        sendMessage.setText("Tasbex 0/33");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
    public static EditMessageReplyMarkup getUpdateTasbex(long chatId,Integer data){
        n ++;
        if (n == 33){
            n = 0;
        }
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();

        inlineKeyboardButton.setText(String.valueOf(n)+"\uD83D\uDCFF");
        inlineKeyboardButton.setCallbackData("tasbex");
        buttons.add(inlineKeyboardButton);
        list.add(buttons);
        inlineKeyboardMarkup.setKeyboard(list);
        editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);

        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId(data);
        editMessageReplyMarkup.setReplyMarkup(inlineKeyboardMarkup);
        return editMessageReplyMarkup;
    }
}
