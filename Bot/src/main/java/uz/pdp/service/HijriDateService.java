package uz.pdp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class HijriDateService {
    public static String fetchHijriDate() {
        String apiUrl = "https://islomapi.uz/api/present/day?region=Toshkent";
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject hijriDate = jsonObject.getJSONObject("hijri_date");
            String month = hijriDate.getString("month");
            int day = hijriDate.getInt("day");
            String weekday = jsonObject.getString("weekday");
            return "Hijri Sana: " + month + " " + day + " (" + weekday + ")";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to fetch Hijri date.";
        }
    }
}
