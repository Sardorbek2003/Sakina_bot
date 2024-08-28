package uz.pdp.service.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.model.Root;
import uz.pdp.model.Times;

import java.util.List;

@UtilityClass
public class BotUtil {

    public static ReplyKeyboardMarkup replyKeyboardMarkup(List<String> data, int n) {
        return new ReplyKeyboardMarkup();
    }
    public static <T> InlineKeyboardMarkup inlineKeyboardMarkup(List<T> models, int n) {
        if (models.isEmpty()) {
            return new InlineKeyboardMarkup();
        }
        T t = models.get(0);
        if (t instanceof Root product) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setCallbackData("P" + product.getRegion());
        }else if(t instanceof Times category) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setCallbackData("C" + category.getAsr());
        }
        return new InlineKeyboardMarkup();
    }

}
