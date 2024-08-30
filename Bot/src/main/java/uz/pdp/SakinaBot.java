package uz.pdp;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.menyu.MenuService;
import uz.pdp.service.*;
import uz.pdp.utill.ObjectUtil;

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
                System.out.println(chatId);
                if (StringUtils.equals(text, START) || text.startsWith(BACK)) {
                    SendMessage sendMessage = MenuService.showMenyu(chatId);
                    execute_(sendMessage);
                } else if (StringUtils.startsWith(text, ISLOMIY_KITOB)) {
                    SendMessage sendMessage = BookBotService.bookMenyu(chatId);
                    sendMessage.setText("Islomiy Kitoblar");
                    execute_(sendMessage);
                } else if (message.getText().equals(NAMOZ_VAQT_2)) {
                    SendMessage sendMessage = PrayerTimeInlineService.sendPrayerTimesKeyboard(chatId);
                    sendMessage.setText(new Date().toString());
                    execute_(sendMessage);
                } else if (message.getText().startsWith(NAMOZ_VAQT)) {
                    SendMessage sendMessage = PrayerTimeService.getNamozVatlari(chatId);
                    sendMessage.setText(NAMOZ_VAQT);
                    execute_(sendMessage);
                } else if (message.getText().startsWith(ENG_YAQIN_NAMOZ)) {
                    SendMessage sendMessage = NearestPrayerTimeService.getNextPrayer(chatId);
                    execute_(sendMessage);
                } else if (message.getText().equals(HIJRIY_YIL_XISOB)) {
                    String hijriDate = HijriDateService.fetchHijriDate();
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(hijriDate);
                    execute_(sendMessage);
                } else if (message.getText().equals(ENG_YAQIN_MASJID)) {
                    SendMessage sendMessage = MosqueService.requestUserLocation(chatId);
                    sendMessage.setText("Yaqin masjidlar");
                    execute_(sendMessage);
                } else if (message.getText().equals(TASBEX)) {
                    SendMessage sendMessage = Tasbex.getTasbex(chatId, "0");
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Tasbex 0/33");
                    execute_(sendMessage);
                } else if (message.getText().equals(QURONI_KARIMDAN_SURAH)) {
                    ObjectUtil.koranService.newMenyu(chatId, 0);
                    SendMessage sendMessage = ObjectUtil.koranBotService.getSurah(chatId);
                    sendMessage.setChatId(chatId);
                    sendMessage.setText(SURAH_STARTS);
                    execute_(sendMessage);
                } else if (text.equals(QURAN)) {
                    SendMessage sendMessage = KoranBotService.surahList(chatId);
                    sendMessage.setText("Suralar");
                    execute_(sendMessage);
                } else if (text.equals(QURAN_SURALARI)) {
                    SendMessage sendMessage = ImamNamesService.imamMenu(chatId);
                    sendMessage.setText("Imom menu");
                    execute_(sendMessage);
                } else if (ImamNamesService.IMAM_MENU.containsKey(text)) {
                    sendMultipleAudios(chatId, text);
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
            Integer inlineMessageId = callbackQuery.getMessage().getMessageId();
            if (data.equals("someCallbackData")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Callback query handled: " + data);
                execute_(sendMessage);
            }
            if (data.equals("next")) {
                ObjectUtil.koranService.newMenyu(chatId, 10);
                EditMessageReplyMarkup surah = ObjectUtil.koranBotService.getSurah(chatId, inlineMessageId);
                execute_update(surah);
            }
            if (data.equals("back")) {
                ObjectUtil.koranService.newMenyu(chatId, -10);
                EditMessageReplyMarkup surah = ObjectUtil.koranBotService.getSurah(chatId, inlineMessageId);
                execute_update(surah);
            }
            if (data.startsWith("surahs")) {
                SendMessage sendMessage = GetSurahUrl.quranMenyu(chatId, data);
                execute_(sendMessage);
            }
            if (data.startsWith("lsuralar")) {
                SendMessage sendMessage = new SendMessage();
                String surah = GetSurahUrl.getLotinSurah(data);
                sendMessage.setChatId(chatId);
                sendMessage.setText(surah);
                execute_(sendMessage);
            }
            if (data.startsWith("asuralar")) {
                SendMessage sendMessage = new SendMessage();
                String surah = GetSurahUrl.getAraSurah(data);
                sendMessage.setChatId(chatId);
                sendMessage.setText(surah);
                execute_(sendMessage);
            }
            if (data.equals("bomdod")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("1.Ният қилиш\n" +
                        "\n" +
                        "Намоз вақти киргач, таҳорат билан, пок кийим билан, покиза жойда туриб, қиблага юзланамиз ва ният қиламиз\n");
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                execute_(sendMessage);
            }
            if (data.equals("tasbex")) {
                EditMessageReplyMarkup markup = Tasbex.getUpdateTasbex(chatId, inlineMessageId);
                execute_update(markup);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "@Uzbekmen_bot";
    }

    @Override
    public String getBotToken() {
        return "7385826567:AAFA8skdLgRWh3-bPGa7qPLh3NkVIMSWIc8";
    }

    private void execute_(SendMessage t) {
        try {
            execute(t);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void execute_update(EditMessageReplyMarkup t) {
        try {
            execute(t);
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

    private void setSendAudio(SendAudio sendAudio) {
        try {
            execute(sendAudio);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public SendAudio sendMultipleAudios(Long chatId, String imomName) {
        SendAudio sendAudio = new SendAudio();
        String pathName = ImamNamesService.IMAM_MENU.get(imomName);
        System.out.println(pathName);
        if (pathName == null) {
            throw new RuntimeException("Imom topilmadi");
        }

        int n = 1;
        for (int i = 1; i < 115; i++) {
            String formatted = String.format("%03d", n);
            String audioUrls = "https://server8.mp3quran.net/" + pathName + "/" + formatted + ".mp3";
            sendAudio.setChatId(chatId);
            System.out.println("Attempting to send audio from URL: " + audioUrls);
            sendAudio.setAudio(new InputFile(audioUrls));
            sendAudio.setCaption("Here is your audio file!");
            setSendAudio(sendAudio);
            n += 1;
        }
        return sendAudio;
    }
}
