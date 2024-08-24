package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.kitoblar.Kitoblar;
import uz.pdp.namozVaqtlar.NamozVaqtlari;
import uz.pdp.namozVaqtlar.NamozVaqtlariInline;

import java.util.Date;

public class SakinaBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            if (text.equals("/start") || message.getText().startsWith("Back")) {
                SendMessage sendMessage = MenyuService.showMenyu(message.getChatId());
                execute_(sendMessage);
            }
           else if (message.getText().startsWith("Back")) {
                SendMessage sendMessage = MenyuService.showMenyu(message.getChatId());
                execute_(sendMessage);
            }
           else if (message.getText().startsWith("Islomiy Kitoblar")) {
                System.out.println("if");
                SendMessage sendMessage = Kitoblar.bookMenyu(message.getChatId());
                sendMessage.setText("Islomiy Kitoblar");
                execute_(sendMessage);
            }
           else if (message.getText().equals("Namoz vaqtlari\uD83D\uDD52")){
                SendMessage sendMessage = NamozVaqtlariInline.sendPrayerTimesKeyboard(message.getChatId());
                sendMessage.setText(new Date().toString());
                execute_(sendMessage);
            }
           else if (message.getText().startsWith("Namoz Vaqtlari")) {
            SendMessage sendMessage = NamozVaqtlari.getNamozVatlari(message.getChatId());
            sendMessage.setText("Namoz Vaqtlari");
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

    private void execute_(SendMessage sendMessage) {  // Metod nomi o'zgartirildi
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();  // Xatolikni to'g'ri ko'rsatish
        }
    }
}