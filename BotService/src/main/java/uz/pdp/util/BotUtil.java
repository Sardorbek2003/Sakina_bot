package uz.pdp.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.model.Koran;
import uz.pdp.model.Surahs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@UtilityClass
public class BotUtil {

    public static ReplyKeyboardMarkup replyKeyboardMarkup(List<String> data, int n) {
        List<KeyboardRow> keyboardRows = makeReplyKeyboardRow(data, n);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    private static List<KeyboardRow> makeReplyKeyboardRow(List<String> data, int n) {
        return IntStream.range(0, (data.size() + n - 1) / n)
                .mapToObj(i -> {
                    KeyboardRow row = new KeyboardRow();
                    data.stream()
                            .skip((long) i * n)
                            .limit(n)
                            .map(KeyboardButton::new)
                            .forEach(row::add);
                    return row;
                })
                .collect(Collectors.toList());
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> InlineKeyboardMarkup inlineKeyboardMarkup(List<String> data, List<String> callBackQueryData, int n) {
        if (data.isEmpty() || callBackQueryData.isEmpty()) {
            return new InlineKeyboardMarkup();
        }

        List<List<InlineKeyboardButton>> keyboardRows = makeInlineKeyboardRows(data, callBackQueryData, n);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }

    private static <T> List<List<InlineKeyboardButton>> makeInlineKeyboardRows(List<T> data, List<T> callBackQueryData, int n) {
        List<List<InlineKeyboardButton>> collect = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            InlineKeyboardButton inlineKeyboardButton = createInlineKeyboardButton(data.get(i), callBackQueryData.get(i));
            buttons.add(inlineKeyboardButton);
            if ((i + 1) % n == 0){
                collect.add(buttons);
                buttons = new ArrayList<>();
            }
        }

        if (!buttons.isEmpty()){
            collect.add(buttons);
        }

        return collect;
    }

    private static <T> InlineKeyboardButton createInlineKeyboardButton(T model, T model2) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        String callbackData = "";

        if (model.equals("Lotin alifbosida")) {
            button.setText((String) model);
            callbackData = (String) model2;
        }
        if (model.equals("باللغة العربية")) {
            button.setText((String)model);
            callbackData = (String)model2;
        }
        if (model.equals("NEXT")){
            button.setText((String) model);
            callbackData = (String) model2;
        }
        if (model.equals("BACK")){
            button.setText((String) model);
            callbackData = (String) model2;
        }

        button.setCallbackData(callbackData);
        return button;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T> InlineKeyboardMarkup inlineKeyboardMarkup(List<T> models, int n) {
        if (models.isEmpty()) {
            return new InlineKeyboardMarkup();
        }

        List<List<InlineKeyboardButton>> keyboardRows = makeInlineKeyboardRows(models, n);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }

    private static <T> List<List<InlineKeyboardButton>> makeInlineKeyboardRows(List<T> models, int n) {
        return IntStream.range(0, (models.size() + n - 1) / n)
                .mapToObj(i -> models.stream()
                        .skip((long) i * n)
                        .limit(n)
                        .map(BotUtil::createInlineKeyboardButton)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static <T> InlineKeyboardButton createInlineKeyboardButton(T model) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        String callbackData = "";

        if (model instanceof Koran surahs) {
            button.setText(surahs.getName());
            callbackData = surahs.getName();
        }
        if (model instanceof Surahs surahs) {
            button.setText(surahs.getId() + "   " + surahs.getTransliteration());
            callbackData = "surahs" + surahs.getTransliteration();
        }

        button.setCallbackData(callbackData);
        return button;
    }

    public static ReplyKeyboardMarkup getRequestPhoneNumberKeyboard() {
        KeyboardButton button = new KeyboardButton("Share your phone number");
        button.setRequestContact(true);

        KeyboardRow row = new KeyboardRow();
        row.add(button);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(row);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboardRows);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        return keyboardMarkup;
    }

//    private static List<InlineKeyboardButton> getNextPrev(String data) {
//        List<InlineKeyboardButton> buttons = new ArrayList<>();
//
//        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
//        inlineKeyboardButton.setText("NEXT");
//        inlineKeyboardButton.setCallbackData("next");
//
//        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
//        inlineKeyboardButton1.setText("BACK");
//        inlineKeyboardButton1.setCallbackData("back");
//
//        buttons.add(inlineKeyboardButton1);
//        buttons.add( inlineKeyboardButton);
//        return buttons;
//    }
}