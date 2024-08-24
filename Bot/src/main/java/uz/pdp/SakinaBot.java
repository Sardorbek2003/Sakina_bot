package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.kitoblar.Kitoblar;

public class SakinaBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            if (text.equals("/start") || text.equals("showMenyu")) {
                SendMessage sendMessage = MenyuService.showMenyu(update);
                execute_(sendMessage);  // Yaxshi nomlangan metodni chaqirish
            }
            if (message.getText().startsWith("Islomiy Kitoblar")) {
                System.out.println("if");


                SendMessage sendMessage = Kitoblar.bookMenyu(message.getChatId());
                sendMessage.setText("Islomiy Kitoblar");
                execute_(sendMessage);
            }
            if (message.getText().startsWith("Back")){
                SendMessage sendMessage = MenyuService.showMenyu(update);
                execute_(sendMessage);  // Yaxshi nomlangan metodni chaqirish
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