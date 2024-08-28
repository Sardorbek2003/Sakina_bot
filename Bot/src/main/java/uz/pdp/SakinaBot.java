package uz.pdp;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.SurahIndex;
import uz.pdp.service.*;
import uz.pdp.service.util.JsonUtil;

import java.util.Date;
import java.util.List;

import static uz.pdp.BotConstants.START;

public class SakinaBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                String text = message.getText();
                long chatId = message.getChatId();
                System.out.println(chatId);
                if (StringUtils.equals(text, START) || text.startsWith("Back")) {
                    System.out.println(message.getChatId());
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
                } else if (text.equals("Tasbex\uD83D\uDCFF")) {
                    SendMessage sendMessage = Tasbex.getTasbex(chatId,"0");
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Tasbex 0/33");
                    execute_(sendMessage);
                } else if (text.equals("Quroni Karimdan Bazi Suralar\uD83D\uDCD6")) {
                    List<SurahIndex> suraIndices = JsonUtil.readGson("BotService/src/test/file/suraIndex.json", new TypeReference<List<SurahIndex>>() {
                    });
                    for (SurahIndex index : suraIndices) {
                        if (index.getChatId() == chatId) {
                            index.setId(10);
                        }
                    }
                    JsonUtil.writeGson(suraIndices, "BotService/src/test/file/suraIndex.json");
                    SendMessage surah = QuroniKarimdanBaziSuralar.getSurah(message.getChatId());
                    execute_(surah);
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
                next(chatId);
                EditMessageReplyMarkup surah = QuroniKarimdanBaziSuralar.getSurah(chatId, inlineMessageId);
                execute_update(surah);
            }
            if (data.equals("back")) {
                back(chatId);
                EditMessageReplyMarkup surah = QuroniKarimdanBaziSuralar.getSurah(chatId, inlineMessageId);
                execute_update(surah);
            }
            if (data.startsWith("suralar")) {
                SendMessage sendMessage = GetSurahUrl.QuranMenyu(chatId, data);
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
           if (data.equals("tasbex")){
               EditMessageReplyMarkup markup = Tasbex.getUpdateTasbex(chatId,inlineMessageId);
               execute_update(markup);
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
    private void next(long chatId) {
        List<SurahIndex> suraIndices = JsonUtil.readGson("BotService/src/test/file/suraIndex.json", new TypeReference<List<SurahIndex>>() {
        });
        for (SurahIndex index : suraIndices) {
            if (index.getChatId() == chatId) {
                if (114 >= index.getId()) {
                    index.setId(index.getId() + 10);
                }
            }
        }
        JsonUtil.writeGson(suraIndices, "BotService/src/test/file/suraIndex.json");
    }
    private void back(long chatId) {
        List<SurahIndex> suraIndices = JsonUtil.readGson("BotService/src/test/file/suraIndex.json", new TypeReference<List<SurahIndex>>() {
        });
        for (SurahIndex index : suraIndices) {
            if (index.getChatId() == chatId) {
                if (10 <= index.getId()) {
                    index.setId(index.getId() - 10);
                }
            }
        }
        JsonUtil.writeGson(suraIndices, "BotService/src/test/file/suraIndex.json");
    }
}
