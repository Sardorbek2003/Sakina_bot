package uz.pdp;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.service.NearestPrayerTimeService;
import uz.pdp.service.MosqueService;
import uz.pdp.service.HijriDateService;
import uz.pdp.service.BookService;
import uz.pdp.service.PrayerTimeService;
import uz.pdp.service.PrayerTimeInlineService;
import uz.pdp.service.MenuService;

import java.util.Date;

import static uz.pdp.BotConstants.*;

public class SakinaBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                String text = message.getText();
                long chatId = message.getChatId();

                if (StringUtils.equals(text, START) || text.startsWith("Back")) {
                    SendMessage sendMessage = MenuService.showMenyu(chatId);
                    execute_(sendMessage);
                } else if (StringUtils.startsWith(text, "Islomiy Kitoblar")) {
                    SendMessage sendMessage = BookService.bookMenyu(chatId);
                    sendMessage.setText("Islomiy Kitoblar");
                    execute_(sendMessage);
                } else if (message.getText().equals("Namoz vaqtlari\uD83D\uDD52")) {
                    SendMessage sendMessage = PrayerTimeInlineService.sendPrayerTimesKeyboard(chatId);
                    sendMessage.setText(new Date().toString());
                    execute_(sendMessage);
                } else if (text.startsWith("Namoz Vaqtlari")) {
                    SendMessage sendMessage = PrayerTimeService.getNamozVatlari(chatId);
                    sendMessage.setText("Namoz Vaqtlari");
                    execute_(sendMessage);
                } else if (text.startsWith("Eng Yaqin Namoz\uD83D\uDCAC")) {
                    SendMessage sendMessage = NearestPrayerTimeService.getNextPrayer(chatId);
                    execute_(sendMessage);
                } else if (text.equals("Xijriy Yil Xisobi\uD83D\uDCC6")) {
                    String hijriDate = HijriDateService.fetchHijriDate();
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(hijriDate);
                    execute_(sendMessage);
                } else if (text.equals("Eng yaqin Masjidlar\uD83D\uDD4C")) {
                    SendMessage sendMessage = MosqueService.requestUserLocation(chatId);
                    sendMessage.setText("Yaqin masjidlar");
                    execute_(sendMessage);
                }
            } else if (message != null && message.hasLocation()) {
                Location userLocation = message.getLocation();
                long chatId = message.getChatId();
                String mapLink = MosqueService.getMapLink(userLocation);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Here is a link to find the nearest mosques on Google Maps:\n" + mapLink);
                execute_(sendMessage);
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            long chatId = callbackQuery.getMessage().getChatId();

            if (data.equals("someCallbackData")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Callback query handled: " + data);
                execute_(sendMessage);
            }
            if (data.equals("bomdod")){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("1.Ният қилиш\n" +
                        "\n" +
                        "Намоз вақти киргач, таҳорат билан, пок кийим билан, покиза жойда туриб, қиблага юзланамиз ва ният қиламиз\n");
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                execute_(sendMessage);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "library_0304_bot";
    }

    @Override
    public String getBotToken() {
        return "7288467792:AAHKQse3hGoUFjZD2ehOTescDE_5rrlod-w";
    }

    private void execute_(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendTextMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
