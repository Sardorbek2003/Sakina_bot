package uz.pdp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class HijriDateService {
    private static final String API_URL = "https://islomapi.uz/api/present/day?region=Toshkent";

    public static String fetchHijriDate() {
        try {
            String jsonResponse = makeHttpRequest(API_URL);
            return parseHijriDate(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to fetch Hijri date.";
        }
    }

    public static String makeHttpRequest(String apiUrl) throws Exception {
        StringBuilder response = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    public static String parseHijriDate(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject hijriDate = jsonObject.getJSONObject("hijri_date");
        String month = hijriDate.getString("month");
        int day = hijriDate.getInt("day");
        String weekday = jsonObject.getString("weekday");
        return "Hijri Sana: " + month + " " + day + " (" + weekday + ")";
    }
}
