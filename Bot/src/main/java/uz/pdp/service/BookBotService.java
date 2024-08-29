package uz.pdp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.util.BotUtil;

import java.util.ArrayList;
import java.util.List;

public class BookBotService {
    public static SendMessage bookMenyu(Long chatId){
        List<String> list = new ArrayList<>();
        list.add("Audio Kitoblar\uD83C\uDFA7");
        list.add("Kitoblar\uD83D\uDCAC");
        list.add("Back⬅\uFE0F");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = BotUtil.replyKeyboardMarkup(list,2);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}