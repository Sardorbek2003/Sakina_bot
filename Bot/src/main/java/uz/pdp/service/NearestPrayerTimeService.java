package uz.pdp.service;

import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NearestPrayerTimeService {
    public static SendMessage getNextPrayer(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getNearestPrayerTime());
        return sendMessage;
    }

    private static String getNearestPrayerTime() {
        String apiUrl = "https://islomapi.uz/api/present/day?region=Toshkent";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder inline = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject jsonObject = new JSONObject(inline.toString());
                JSONObject times = jsonObject.getJSONObject("times");

                LocalDateTime currentDateTime = LocalDateTime.now();
                return findNearestPrayer(currentDateTime, times);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error: Unable to get prayer times.";
    }

    private static String findNearestPrayer(LocalDateTime currentDateTime, JSONObject times) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime bomdodDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(times.getString("tong_saharlik"), timeFormatter));
        LocalDateTime quyoshDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(times.getString("quyosh"), timeFormatter));
        LocalDateTime peshinDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(times.getString("peshin"), timeFormatter));
        LocalDateTime asrDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(times.getString("asr"), timeFormatter));
        LocalDateTime shomDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(times.getString("shom_iftor"), timeFormatter));
        LocalDateTime xuftomDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), LocalTime.parse(times.getString("hufton"), timeFormatter));
        LocalDateTime[] prayerDateTimes = {bomdodDateTime, quyoshDateTime, peshinDateTime, asrDateTime, shomDateTime, xuftomDateTime};
        String[] prayerNames = {"Bomdod", "Quyosh", "Peshin", "Asr", "Shom", "Hufton"};
        LocalDateTime nextPrayerDateTime = null;
        String nextPrayerName = "";
        Duration shortestDuration = Duration.ofDays(1);

        for (int i = 0; i < prayerDateTimes.length; i++) {
            LocalDateTime prayerDateTime = prayerDateTimes[i];
            String prayerName = prayerNames[i];
            if (currentDateTime.isAfter(prayerDateTime)) {
                prayerDateTime = prayerDateTime.plusDays(1);
            }
            Duration duration = Duration.between(currentDateTime, prayerDateTime);
            if (duration.isNegative()) {
                duration = duration.plusDays(1);
            }
            if (duration.compareTo(shortestDuration) < 0) {
                shortestDuration = duration;
                nextPrayerDateTime = prayerDateTime;
                nextPrayerName = prayerName;
            }
        }
        long hours = shortestDuration.toHours();
        long minutes = shortestDuration.toMinutes() % 60;
        return String.format("Keyingi namoz vaqti bu %s: %d soat va %d daqiqa qoldi \uD83D\uDE0A", nextPrayerName, hours, minutes);
    }
}
