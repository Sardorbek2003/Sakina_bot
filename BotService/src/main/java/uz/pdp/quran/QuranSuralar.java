package uz.pdp.quran;

import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class QuranSuralar {

    public static SendAudio sendAudio(Long chatId){

        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(chatId);
        sendAudio.setAudio(new InputFile("https://www.truemuslims.net/Quran/Russian/001.mp3"));
         sendAudio.setCaption("Here is your audio file!");
        sendAudio.setTitle("Sample Track");
        sendAudio.setPerformer("Artist Name");
        return sendAudio;
    }
}
