package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.engYaqinNamoz.EngYaqinNamoz;
import uz.pdp.hijriSana.HijriSana;
import uz.pdp.kitoblar.Kitoblar;
import uz.pdp.namozVaqtlar.NamozVaqtlari;
import uz.pdp.namozVaqtlar.NamozVaqtlariInline;

import java.util.Date;

public class SakinaBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                String text = message.getText();
                if (text.equals("/start") || text.startsWith("Back")) {
                    SendMessage sendMessage = MenyuService.showMenyu(message.getChatId());
                    execute_(sendMessage);
                } else if (text.startsWith("Islomiy Kitoblar")) {
                    SendMessage sendMessage = Kitoblar.bookMenyu(message.getChatId());
                    sendMessage.setText("Islomiy Kitoblar");
                    execute_(sendMessage);
                } else if (text.equals("Namoz vaqtlari\uD83D\uDD52")) {
                    SendMessage sendMessage = NamozVaqtlariInline.sendPrayerTimesKeyboard(message.getChatId());
                    sendMessage.setText(new Date().toString());
                    execute_(sendMessage);
                } else if (text.startsWith("Namoz Vaqtlari")) {
                    SendMessage sendMessage = NamozVaqtlari.getNamozVatlari(message.getChatId());
                    sendMessage.setText("Namoz Vaqtlari");
                    execute_(sendMessage);
                } else if (text.startsWith("Eng Yaqin Namoz\uD83D\uDCAC")) {
                    SendMessage sendMessage = EngYaqinNamoz.getNextPrayer(message.getChatId());
                    execute_(sendMessage);
                }
                else if (text.equals("Xijriy Yil Xisobi\uD83D\uDCC6")) {
                    String hijriDate = HijriSana.fetchHijriDate();
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText(hijriDate);
                    execute_(sendMessage);
                }
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
        }
        if (update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("next")){

            }
        }
    }
    @Override
    public String getBotUsername() {
        return "library_0304_bot";  // Username qismini qaytarish
    }
    @Override
    public String getBotToken() {
        return "7288467792:AAHKQse3hGoUFjZD2ehOTescDE_5rrlod-w";  // Tokenni to'g'ri kiriting
    }
    private void execute_(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();  // Xatolikni to'g'ri ko'rsatish
        }
    }
}
