package uz.pdp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MosqueService {

    public static SendMessage requestUserLocation(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Please share your location to find the nearest mosque.");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        KeyboardButton locationButton = new KeyboardButton();
        locationButton.setText("Share Location");
        locationButton.setRequestLocation(true);

        KeyboardButton backButton = new KeyboardButton();
        backButton.setText("Back");
        KeyboardRow row = new KeyboardRow();
        row.add(locationButton);
        row.add(backButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    public static String getMapLink(Location userLocation) {
        double latitude = userLocation.getLatitude();
        double longitude = userLocation.getLongitude();

        // Google Maps URL for nearby mosques
        return String.format(
                "https://www.google.com/maps/search/?api=1&query=mosque&location=%f,%f",
                latitude, longitude);
    }
}
